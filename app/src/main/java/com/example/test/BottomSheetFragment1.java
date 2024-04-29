package com.example.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BottomSheetFragment1 extends BottomSheetDialogFragment {

    private List<String> itemList = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4"); // 예시 리스트
    private List<String> checkedItems = new ArrayList<>(); // 체크된 항목을 저장할 리스트

    public interface CheckedItemsListener {
        void onCheckedItemsChanged(List<String> checkedItems);

//        // BottomSheetFragment1에서 선택된 아이템을 받아옵니다.
//        void onCheckedItemsChangedBottomSheet1(List<String> checkedItems);
//
//        void onCheckedItemsChangedBottomSheet2(List<String> checkedItems);
    }

    private CheckedItemsListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet_layout1, container, false);
        FrameLayout checkboxLayout = view.findViewById(R.id.bottom_sheet_container);
        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);

        int marginTop = 180;
        int marginLeft = 130;
        int marginBetweenCheckboxes = 110;

        for (String item : itemList) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(item);
            checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

            // SharedPreferences에서 저장된 상태를 불러와서 설정하되, 기본값을 false로 설정
            boolean isChecked = sharedPreferences.getBoolean(item, false);
            checkBox.setChecked(isChecked);

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(marginLeft, marginTop, 0, 0);
            checkBox.setLayoutParams(layoutParams);

            checkboxLayout.addView(checkBox);

            if (isChecked) {
                checkedItems.add(item);
            }

            checkBox.setOnCheckedChangeListener((buttonView, isChecked1) -> {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(item, isChecked1);
                editor.apply();

                if (isChecked1) {
                    checkedItems.add(item);
                } else {
                    checkedItems.remove(item);
                }
                mListener.onCheckedItemsChanged(checkedItems);
            });

            marginTop += marginBetweenCheckboxes;
        }

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (CheckedItemsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 두 번째 레이아웃으로 이동할 때 선택된 항목에 해당하는 텍스트뷰를 설정합니다.
        View fragmentSecondView = requireActivity().findViewById(R.id.fragmentContainerView);
        TextView selectedItemsTextView = fragmentSecondView.findViewById(R.id.second_bottom);

        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        StringBuilder selectedItemsText = new StringBuilder();
        for (String item : itemList) {
            if (sharedPreferences.getBoolean(item, false)) {
                selectedItemsText.append(item).append("\n");
            }
        }
        selectedItemsTextView.setText(selectedItemsText.toString());
    }
}
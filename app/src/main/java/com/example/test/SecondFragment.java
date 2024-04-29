package com.example.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends Fragment {
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

//!!

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        textView = view.findViewById(R.id.second_bottom);


        // 첫 번째 버튼 - 바텀 시트
        Button openBottomSheetButton1 = view.findViewById(R.id.btSheet_button1);
        openBottomSheetButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 바텀시트를 띄우는 코드
                BottomSheetFragment1 bottomSheetFragment = new BottomSheetFragment1();
                bottomSheetFragment.show(getParentFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        // 두 번째 버튼 - 바텀 시트
        Button openBottomSheetButton2 = view.findViewById(R.id.btSheet_button2);
        openBottomSheetButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 바텀시트를 띄우는 코드
                BottomSheetFragment2 bottomSheetFragment = new BottomSheetFragment2();
                bottomSheetFragment.show(getParentFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        // 세 번째 버튼 - 바텀 시트
        Button openBottomSheetButton3 = view.findViewById(R.id.btSheet_button3);
        openBottomSheetButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 바텀시트를 띄우는 코드
                BottomSheetFragment3 bottomSheetFragment = new BottomSheetFragment3();
                bottomSheetFragment.show(getParentFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        return view;
    }
    public void updateCheckedItems(List<String> checkedItems) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : checkedItems) {
            stringBuilder.append(item).append("\n");
        }
        textView.setText(stringBuilder.toString());
    }


}
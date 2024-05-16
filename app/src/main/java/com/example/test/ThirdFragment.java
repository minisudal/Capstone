package com.example.test;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {
    private EditText editTextSecondActivity4;
    private TextView textViewSecondActivity4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_third, container, false);

        Button button1 = rootView.findViewById(R.id.tipbutton1);
        Button button2 = rootView.findViewById(R.id.tipbutton2);
        Button button3 = rootView.findViewById(R.id.tipbutton3);
        Button button4 = rootView.findViewById(R.id.tipbutton4);
        Button button5 = rootView.findViewById(R.id.tipbutton5);

        button1.setOnClickListener(v -> showDialog(R.layout.fragment_tip1));
        button2.setOnClickListener(v -> showDialog(R.layout.fragment_tip2));
        button3.setOnClickListener(v -> showDialog(R.layout.fragment_tip3));
        button4.setOnClickListener(v -> showDialog(R.layout.fragment_tip4));
        button5.setOnClickListener(v -> showDialog(R.layout.fragment_tip5));

        return rootView;
    }

    private void showDialog(int layoutResId) {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layoutResId);

        // Find the ImageButton within the dialog layout
        ImageButton imageButton = dialog.findViewById(R.id.imageButton);

        // ImageButton 클릭 이벤트 처리
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dialog 닫기
                dialog.dismiss();
            }
        });

        if (layoutResId == R.layout.fragment_tip5) {
            // Find views in fragment_tip5 layout
            editTextSecondActivity4 = dialog.findViewById(R.id.editText);
            textViewSecondActivity4 = dialog.findViewById(R.id.textViewSecondActivity4);

            // 저장된 메모 불러오기
            loadMemo();

            // 저장 버튼 클릭 이벤트 처리
            Button saveButtonSecondActivity4 = dialog.findViewById(R.id.saveButton);
            saveButtonSecondActivity4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String inputText = editTextSecondActivity4.getText().toString();
                    String existingText = textViewSecondActivity4.getText().toString();
                    String newText = existingText + "\n" + inputText; // 기존 텍스트에 추가
                    textViewSecondActivity4.setText(newText); // 새로운 텍스트 설정
                    editTextSecondActivity4.setText(""); // 입력 필드 초기화
                    // 메모를 저장합니다.
                    saveMemo(newText);
                }
            });

            // 삭제 버튼 클릭 이벤트 처리
            Button deleteButton = dialog.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 여기서 메모 삭제 로직을 작성합니다.
                    // SharedPreferences를 사용하여 저장된 메모를 삭제합니다.
                    deleteMemo();

                    // 삭제 후에는 필요에 따라 화면을 업데이트할 수 있습니다.
                    textViewSecondActivity4.setText(""); // 예를 들어, 저장된 메모가 화면에 보여지는 경우 삭제 후에 비워줍니다.
                }
            });
        }

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawableResource(R.drawable.tipfragment_bordor); // 배경 drawable 적용
        }

        dialog.show();
    }

    private void saveMemo(String memo) {
        SharedPreferences preferences = requireContext().getSharedPreferences("memo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("memo_text", memo);
        editor.apply();
    }

    // 메모 불러오기
    private void loadMemo() {
        SharedPreferences preferences = requireContext().getSharedPreferences("memo", Context.MODE_PRIVATE);
        String memo = preferences.getString("memo_text", "");
        textViewSecondActivity4.setText(memo);
    }

    // 메모 삭제
    private void deleteMemo() {
        SharedPreferences preferences = requireContext().getSharedPreferences("memo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("memo_text");
        editor.apply();
    }

}

package com.example.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class TipFragment4 extends Fragment {
    private EditText editTextSecondActivity4;
    private TextView textViewSecondActivity4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 프래그먼트의 레이아웃을 설정합니다.
        View rootView = inflater.inflate(R.layout.fragment_tip4, container, false);

        editTextSecondActivity4 = rootView.findViewById(R.id.editText);
        Button saveButtonSecondActivity4 = rootView.findViewById(R.id.saveButton);
        textViewSecondActivity4 = rootView.findViewById(R.id.textViewSecondActivity4);

        // 저장된 메모 불러오기
        loadMemo();

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

        // 이 코드는 현재의 Activity 또는 Fragment에 위치해야 합니다.

        Button deleteButton = rootView.findViewById(R.id.deleteButton);
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
        return rootView;
    }


    // 메모 저장
    private void saveMemo(String memo) {
        SharedPreferences preferences = getActivity().getSharedPreferences("memo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("memo_text", memo);
        editor.apply();
    }

    // 메모 불러오기
    private void loadMemo() {
        SharedPreferences preferences = getActivity().getSharedPreferences("memo", Context.MODE_PRIVATE);
        String memo = preferences.getString("memo_text", "");
        textViewSecondActivity4.setText(memo);
    }

    // 메모 삭제
    private void deleteMemo() {
        SharedPreferences preferences = getActivity().getSharedPreferences("memo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("memo_text");
        editor.apply();
    }
}


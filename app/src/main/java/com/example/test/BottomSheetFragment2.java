package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment2 extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.bottom_sheet_layout1, container, false);
//
////        // 바텀시트 내 버튼의 클릭 이벤트 처리
////        Button bottomSheetButton = view.findViewById(R.id.bottomSheetButton);
////        bottomSheetButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                dismiss(); // 바텀시트를 닫습니다.
////            }
////        });
//
//        return view;

        return inflater.inflate(R.layout.bottom_sheet_layout2, container, false);
    }
}

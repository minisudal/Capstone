package com.example.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // 버튼 클릭 이벤트 처리
        Button button = view.findViewById(R.id.button1); // 수정된 ID 사용
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 두 번째 프래그먼트로 이동
                navigateToSecondFragment();
                // 네비게이션 바의 아이템을 두 번째 아이템으로 선택
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).selectSecondFragment();
                }
            }
        });

        return view;
    }

    private void navigateToSecondFragment() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, new SecondFragment())
                    .addToBackStack(null) // 뒤로 가기 동작을 위해 백 스택에 추가
                    .commit();
        }
    }
}
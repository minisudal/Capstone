package com.example.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        Button button = rootView.findViewById(R.id.fridge);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity로 전환 요청을 보냄
                if (getActivity() != null && getActivity() instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    // 카테고리 프래그먼트로 전환
                    mainActivity.replaceFragment(new SecondFragment());
                    // 바텀 네비게이션 뷰의 아이템을 '카테고리'로 선택
                    mainActivity.selectBottomNavigationItem(R.id.secondFragment);
                }
            }
        });

        return rootView;
    }

}
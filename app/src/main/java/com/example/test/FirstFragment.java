//package com.example.test;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//
//public class FirstFragment extends Fragment {
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
//
//        Button button = rootView.findViewById(R.id.fridge);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // MainActivity로 전환 요청을 보냄
//                if (getActivity() != null && getActivity() instanceof MainActivity) {
//                    MainActivity mainActivity = (MainActivity) getActivity();
//                    // 카테고리 프래그먼트로 전환
//                    mainActivity.replaceFragment(new SecondFragment());
//                    // 바텀 네비게이션 뷰의 아이템을 '카테고리'로 선택
//                    mainActivity.selectBottomNavigationItem(R.id.secondFragment);
//                }
//            }
//        });
//
//        return rootView;
//    }
//
//}

// FirstFragment.java
// FirstFragment.java
package com.example.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Set;

public class FirstFragment extends Fragment {
    public boolean hasCheckedItems() {
        CategoryViewModel viewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        Set<String> addedTexts = viewModel.getAddedTexts();
        return !((Set<?>) addedTexts).isEmpty();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        ImageView fridgeImage = rootView.findViewById(R.id.fridge);
        TextView fridgeText = rootView.findViewById(R.id.fridgeText);
        LinearLayout menuLayout = rootView.findViewById(R.id.menuLayout);
        Button button = rootView.findViewById(R.id.Memo);

        if (hasCheckedItems()) {
            // 체크된 항목이 있는 경우 리스트를 표시
            menuLayout.setVisibility((View.VISIBLE));
            homeMenu();

            fridgeText.setVisibility((View.INVISIBLE));
            fridgeImage.setVisibility((View.INVISIBLE));
        } else {
            // 체크된 항목이 없는 경우 이미지 뷰를 표시
            fridgeText.setVisibility(View.VISIBLE);
            fridgeImage.setVisibility(View.VISIBLE);

            menuLayout.setVisibility((View.INVISIBLE));
        }
        fridgeImage.setOnClickListener(new View.OnClickListener() {
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

        button.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), TipActivity.class);
            startActivity(intent);
        });

        return rootView;
    }

    public void homeMenu(){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.menuLayout, new HomeMenuFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

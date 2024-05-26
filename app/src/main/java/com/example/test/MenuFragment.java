//package com.example.test;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MenuFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class MenuFragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public MenuFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MenuFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MenuFragment newInstance(String param1, String param2) {
//        MenuFragment fragment = new MenuFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_menu, container, false);
//    }
//}

package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Set;

public class MenuFragment extends Fragment {

    private CategoryViewModel viewModel;
    private SharedViewModel sharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // SharedViewModel에서 선택 여부 변경을 관찰
        sharedViewModel.getIsItemSelected().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isItemSelected) {
                // Potato와 Carrot 체크박스가 모두 선택되었는지 확인
                boolean isPotatoSelected = viewModel.isChecked(R.id.Potato);
                boolean isCarrotSelected = viewModel.isChecked(R.id.Carrot);

                // Potato와 Carrot 체크박스가 모두 선택되었다면 버튼을 표시
                if (isPotatoSelected && isCarrotSelected) {
                    // fragment_menu 레이아웃에 있는 버튼을 표시하는 로직 추가
                    View button1234 = requireView().findViewById(R.id.button1234);
                    button1234.setVisibility(View.VISIBLE);
                } else {
                    // Potato와 Carrot 체크박스 중 하나라도 선택되지 않았다면 버튼을 숨김
                    View button1234 = requireView().findViewById(R.id.button1234);
                    button1234.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // fragment_menu 레이아웃을 inflate하여 View로 반환
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 추가적으로 필요한 로직이 있다면 이곳에 추가
    }
}


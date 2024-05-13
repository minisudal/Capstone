package com.example.test;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ThirdFragment extends Fragment {

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
//        button4.setOnClickListener(v -> showDialog(R.layout.fragment_tip4));
        // button4 클릭 시 TipFragment5로 이동하도록 수정
        button4.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), TipActivity.class);
            startActivity(intent);
        });
        button5.setOnClickListener(v -> showDialog(R.layout.fragment_tip5));

        return rootView;
    }

    private void showDialog(int layoutResId) {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layoutResId);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(R.drawable.tipfragment_bordor); // 배경 drawable 적용
            window.setGravity(Gravity.BOTTOM);
        }

        dialog.show();
    }
}

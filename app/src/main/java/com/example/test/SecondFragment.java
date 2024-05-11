package com.example.test;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SecondFragment extends Fragment {
    private CategoryViewModel viewModel;
    Button button1, button2, button3, button4, button5, button6;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        // Initialize buttons
        button1 = rootView.findViewById(R.id.button1);
        button2 = rootView.findViewById(R.id.button2);
        button3 = rootView.findViewById(R.id.button3);
        button4 = rootView.findViewById(R.id.button4);
        button5 = rootView.findViewById(R.id.button5);
        button6 = rootView.findViewById(R.id.button6);

        // Set click listeners for each button
        button1.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_vegetable));
        button2.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_meat));
        button3.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_seafood));
        button4.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_condiment));
        button5.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_dairy));
        button6.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_others));

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDataFromViewModel();
    }

    private void loadDataFromViewModel() {
        //viewModel.loadAddedTexts(requireContext());
        Set<String> addedTexts = viewModel.getAddedTexts();
        LinearLayout myIngredient = requireView().findViewById(R.id.myIngredient);

        // Iterate through the set of added texts and create TextViews for each one
        for (String text : addedTexts) {
            TextView textView = new TextView(requireContext());
            textView.setText(text);
            myIngredient.addView(textView);
        }
    }

    private void showDialog(int layoutResId) {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layoutResId);

        LinearLayout myIngredient = requireView().findViewById(R.id.myIngredient);

        // Array of checkbox IDs
        int[] checkboxIds = {R.id.checkBox1, R.id.checkBox2, R.id.checkBox3, R.id.checkBox4,
                R.id.checkBox5, R.id.checkBox6, R.id.checkBox7, R.id.checkBox8,
                R.id.checkBox9, R.id.checkBox10, R.id.checkBox11, R.id.checkBox12,
                R.id.checkBox13, R.id.checkBox14}; // Add more checkbox IDs as needed

        for (int checkboxId : checkboxIds) {
            CheckBox checkBox = dialog.findViewById(checkboxId);
            if (checkBox == null) {
                // Handle the case where checkBox is null
                continue;
            }
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                String newText = checkBox.getText().toString();
                if (isChecked) {
                    TextView textView = new TextView(requireContext());
                    textView.setText(newText);
                    if (!viewModel.isChecked(checkboxId)) {
                        myIngredient.addView(textView);
                        viewModel.setChecked(checkboxId, isChecked, newText);
                    }
                } else {
                    removeTextViewByText(myIngredient, newText);
                    viewModel.setChecked(checkboxId, isChecked, newText);
                }
            });

            checkBox.setChecked(viewModel.isChecked(checkboxId));
        }

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(R.drawable.bottom_sheet_border); // 배경 drawable 적용
            window.setGravity(Gravity.BOTTOM);
        }

        dialog.show();
    }


    // Method to remove TextView with specified text from a ViewGroup
    private void removeTextViewByText(ViewGroup viewGroup, String textToRemove) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof TextView) {
                TextView textView = (TextView) child;
                if (textView.getText().toString().equals(textToRemove)) {
                    viewGroup.removeViewAt(i);
                    return;
                }
            }
        }
    }


}
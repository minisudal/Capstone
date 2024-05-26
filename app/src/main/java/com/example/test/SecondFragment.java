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
        int[] checkboxIds = {R.id.Green_Onion, R.id.Onion, R.id.Potato, R.id.Carrot, R.id.Chili, R.id.Cheongyang_Hot_Pepper, R.id.Garlic, R.id.Minced_Garlic,
                R.id.Perilla_leaf, R.id.Cabbage, R.id.Leek, R.id.Bean_Sprouts, R.id.Zucchini, R.id.Paprika, R.id.Enoki_Mushroom, R.id.King_Oyster_Mushroom,
                R.id.Pork_Belly, R.id.Beef, R.id.Pork_FrontLeg, R.id.Pork_BackLeg, R.id.Minced_Pork, R.id.Minced_Meet, R.id.Chicken_Leg_Meat, R.id.Chicken_Breast,
                R.id.Sea_Mustard, R.id.Shrimp, R.id.Sugar, R.id.Salt, R.id.Pepper, R.id.Red_Pepper_Powder, R.id.Aged_Soy_Sauce, R.id.Brewed_Soy_Sauce, R.id.Red_Pepper_Paste,
                R.id.Fermented_Soybean_Paste, R.id.Ssamjang, R.id.Ketchup, R.id.Mayonnaise, R.id.Mustard, R.id.Sesame_Oil, R.id.Starch_Syrup, R.id.Oyster_Sauce, R.id.Salted_Anchovy_Sauce,
                R.id.Eggs, R.id.Quail_Eggs, R.id.Mozzarella_Cheese, R.id.Sliced_Cheese, R.id.Milk, R.id.Cooking_Oil, R.id.Spam, R.id.Sausages, R.id.Salmon, R.id.Mackerel,
                R.id.Tuna_Can, R.id.Shrimp, R.id.Crab, R.id.Squid, R.id.Octopus, R.id.Mussel, R.id.Scallop, R.id.Clam, R.id.Morning_Bread, R.id.Rice, R.id.Noodles, R.id.Extra_Udon_Noodles,
                R.id.Potato_Starch, R.id.Pan_Frying_Powder, R.id.Rice_Cake, R.id.Cellophane_Noodles, R.id.Fish_Cake, R.id.Water}; // Add more checkbox IDs as needed

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
// SecondFragment.java
//package com.example.test;
//
//import android.app.Dialog;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.Set;
//
//public class SecondFragment extends Fragment {
//    private CategoryViewModel viewModel;
//    private SharedViewModel sharedViewModel;
//    Button button1, button2, button3, button4, button5, button6;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        viewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
//        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//
//        // 액티비티로부터 SharedViewModel을 가져옵니다.
//        MainActivity activity = (MainActivity) requireActivity();
//        sharedViewModel = activity.getSharedViewModel();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
//
//        button1 = rootView.findViewById(R.id.button1);
//        button2 = rootView.findViewById(R.id.button2);
//        button3 = rootView.findViewById(R.id.button3);
//        button4 = rootView.findViewById(R.id.button4);
//        button5 = rootView.findViewById(R.id.button5);
//        button6 = rootView.findViewById(R.id.button6);
//
//        button1.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_vegetable));
//        button2.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_meat));
//        button3.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_seafood));
//        button4.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_dairy));
//        button5.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_condiment));
//        button6.setOnClickListener(v -> showDialog(R.layout.bottom_sheet_others));
//
//        return rootView;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        loadDataFromViewModel();
//    }
//
//    private void loadDataFromViewModel() {
//        Set<String> addedTexts = viewModel.getAddedTexts();
//        LinearLayout myIngredient = requireView().findViewById(R.id.myIngredient);
//
//        for (String text : addedTexts) {
//            TextView textView = new TextView(requireContext());
//            textView.setText(text);
//            myIngredient.addView(textView);
//        }
//    }
//
//    private void showDialog(int layoutResId) {
//        final Dialog dialog = new Dialog(requireContext());
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(layoutResId);
//
//        LinearLayout myIngredient = requireView().findViewById(R.id.myIngredient);
//
//        int[] checkboxIds = {R.id.Green_Onion, R.id.Onion, R.id.Potato, R.id.Carrot, R.id.Chili, R.id.Cheongyang_Hot_Pepper, R.id.Garlic, R.id.Minced_Garlic,
//                R.id.Perilla_leaf, R.id.Cabbage, R.id.Leek, R.id.Bean_Sprouts, R.id.Zucchini, R.id.Paprika, R.id.Enoki_Mushroom, R.id.King_Oyster_Mushroom,
//                R.id.Pork_Belly, R.id.Beef, R.id.Pork_FrontLeg, R.id.Pork_BackLeg, R.id.Minced_Pork, R.id.Minced_Meet, R.id.Chicken_Leg_Meat, R.id.Chicken_Breast,
//                R.id.Sea_Mustard, R.id.Shrimp, R.id.Sugar, R.id.Salt, R.id.Pepper, R.id.Red_Pepper_Powder, R.id.Aged_Soy_Sauce, R.id.Brewed_Soy_Sauce, R.id.Red_Pepper_Paste,
//                R.id.Fermented_Soybean_Paste, R.id.Ssamjang, R.id.Ketchup, R.id.Mayonnaise, R.id.Mustard, R.id.Sesame_Oil, R.id.Starch_Syrup, R.id.Oyster_Sauce, R.id.Salted_Anchovy_Sauce,
//                R.id.Eggs, R.id.Quail_Eggs, R.id.Mozzarella_Cheese, R.id.Sliced_Cheese, R.id.Milk, R.id.Cooking_Oil, R.id.Spam, R.id.Sausages, R.id.Salmon, R.id.Mackerel,
//                R.id.Tuna_Can, R.id.Shrimp, R.id.Crab, R.id.Squid, R.id.Octopus, R.id.Mussel, R.id.Scallop, R.id.Clam, R.id.Morning_Bread, R.id.Rice, R.id.Noodles, R.id.Extra_Udon_Noodles,
//                R.id.Potato_Starch, R.id.Pan_Frying_Powder, R.id.Rice_Cake, R.id.Cellophane_Noodles, R.id.Fish_Cake, R.id.Water};
//
//        for (int checkboxId : checkboxIds) {
//            CheckBox checkBox = dialog.findViewById(checkboxId);
//            if (checkBox == null) {
//                continue;
//            }
//            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//                String newText = checkBox.getText().toString();
//                if (isChecked) {
//                    TextView textView = new TextView(requireContext());
//                    textView.setText(newText);
//                    if (!viewModel.isChecked(checkboxId)) {
//                        myIngredient.addView(textView);
//                        viewModel.setChecked(checkboxId, isChecked, newText);
//                        sharedViewModel.setItemSelected(true); // LiveData 업데이트
//                    }
//                } else {
//                    removeTextViewByText(myIngredient, newText);
//                    viewModel.setChecked(checkboxId, isChecked, newText);
//                    sharedViewModel.setItemSelected(false); // 선택 해제 시에도 LiveData 업데이트
//                }
//            });
//
//            checkBox.setChecked(viewModel.isChecked(checkboxId));
//        }
//
//        Window window = dialog.getWindow();
//        if (window != null) {
//            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            window.setBackgroundDrawableResource(R.drawable.bottom_sheet_border);
//            window.setGravity(Gravity.BOTTOM);
//        }
//
//        dialog.show();
//    }
//
//    private void removeTextViewByText(ViewGroup viewGroup, String textToRemove) {
//        for (int i = 0; i < viewGroup.getChildCount(); i++) {
//            View child = viewGroup.getChildAt(i);
//            if (child instanceof TextView) {
//                TextView textView = (TextView) child;
//                if (textView.getText().toString().equals(textToRemove)) {
//                    viewGroup.removeView(textView);
//                    break;
//                }
//            }
//        }
//    }
//}
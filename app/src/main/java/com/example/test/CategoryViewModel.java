package com.example.test;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

//public class CategoryViewModel extends ViewModel {
//    // Map to store the checked state for each checkbox
//    private final Map<Integer, Boolean> checkboxStates = new HashMap<>();
//    private final Map<Integer, String> checkboxTexts = new HashMap<>();
//    private final Set<String> addedTexts = new LinkedHashSet<>();
//
//    // Method to get the checked state of a checkbox
//    public boolean isChecked(int checkboxId) {
//        // Default to false if the checkbox state is not stored yet
//        return Boolean.TRUE.equals(checkboxStates.getOrDefault(checkboxId, false));
//    }
//
//    // Method to set the checked state of a checkbox
//    public void setChecked(int checkboxId, boolean checked, String text) {
//        checkboxStates.put(checkboxId, checked);
//        if (checked) {
//            addText(text);
//        } else {
//            removeText(text);
//        }
//    }
//
//    // Method to remove text from the set of added texts
//    public void removeText(String text) {
//        addedTexts.remove(text);
//    }
//
//    // Method to add text to the set of added texts
//    public void addText(String text) {
//        addedTexts.add(text);
//    }
//
//    // Method to get the set of added texts
//    public Set<String> getAddedTexts() {
//        return addedTexts;
//    }
//}
public class CategoryViewModel extends ViewModel {
    private SharedViewModel sharedViewModel;
    private final Map<Integer, Boolean> checkboxStates = new HashMap<>();
    private final Map<Integer, String> checkboxTexts = new HashMap<>();
    private final Set<String> addedTexts = new LinkedHashSet<>();
    private int checkedItemCount = 0; // 현재 체크된 항목의 개수

    public boolean isChecked(int checkboxId) {
        return Boolean.TRUE.equals(checkboxStates.getOrDefault(checkboxId, false));
    }

    public void setChecked(int checkboxId, boolean checked, String text) {
        boolean wasChecked = isChecked(checkboxId);
        checkboxStates.put(checkboxId, checked);
        if (checked) {
            checkedItemCount++;
            addText(text);
        } else{
            checkedItemCount--;
            removeText(text);
        }

        // sharedViewModel이 null인지 확인합니다.
        if (sharedViewModel != null) {
            // 체크박스 상태 변경 시에만 sharedViewModel.setItemSelected() 호출
            if (checkedItemCount > 0) {
                sharedViewModel.setItemSelected(true);
            } else if (checkedItemCount == 0) {
                sharedViewModel.setItemSelected(false);
            }
        } else {
            // sharedViewModel이 null인 경우 예외 처리합니다.
            Log.e("SecondFragment", "sharedViewModel is null");
            // 또는 적절한 예외 처리를 수행합니다.
        }
    }

    public void removeText(String text) {
        addedTexts.remove(text);
    }

    public void addText(String text) {
        addedTexts.add(text);
    }

    public Set<String> getAddedTexts() {
        return addedTexts;
    }

    // 현재 체크된 항목의 개수 반환
    public int getCheckedItemCount() {
        return checkedItemCount;
    }

    // 체크된 항목이 하나 이상인지 여부 반환
    public boolean hasCheckedItems() {
        return checkedItemCount > 0;
    }
}

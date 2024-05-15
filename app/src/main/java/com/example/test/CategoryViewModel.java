package com.example.test;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class CategoryViewModel extends ViewModel {
    // Map to store the checked state for each checkbox
    private final Map<Integer, Boolean> checkboxStates = new HashMap<>();
    private final Map<Integer, String> checkboxTexts = new HashMap<>();
    private final Set<String> addedTexts = new LinkedHashSet<>();

    // Method to get the checked state of a checkbox
    public boolean isChecked(int checkboxId) {
        // Default to false if the checkbox state is not stored yet
        return Boolean.TRUE.equals(checkboxStates.getOrDefault(checkboxId, false));
    }

    // Method to set the checked state of a checkbox
    public void setChecked(int checkboxId, boolean checked, String text) {
        checkboxStates.put(checkboxId, checked);
        if (checked) {
            addText(text);
        } else {
            removeText(text);
        }
    }

    // Method to remove text from the set of added texts
    public void removeText(String text) {
        addedTexts.remove(text);
    }

    // Method to add text to the set of added texts
    public void addText(String text) {
        addedTexts.add(text);
    }

    // Method to get the set of added texts
    public Set<String> getAddedTexts() {
        return addedTexts;
    }
}

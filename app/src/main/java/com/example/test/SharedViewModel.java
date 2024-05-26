// SharedViewModel.java
package com.example.test;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isItemSelected = new MutableLiveData<>();

    public void setItemSelected(boolean isSelected) {
        isItemSelected.setValue(isSelected);
    }

    public LiveData<Boolean> getIsItemSelected() {
        return isItemSelected;
    }
    private final CategoryViewModel categoryViewModel = new CategoryViewModel();

    public CategoryViewModel getCategoryViewModel() {
        return categoryViewModel;
    }
}

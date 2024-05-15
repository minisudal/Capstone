package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        int layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
        return inflater.inflate(layoutResId, container, false);
    }
}

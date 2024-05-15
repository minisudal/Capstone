package com.example.test;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import com.example.test.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment((new FirstFragment()));

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            // 리소스 ID를 변수로 저장
            int itemId = item.getItemId();

            if (itemId == R.id.firstFragment) {
                replaceFragment((new FirstFragment()));
            } else if (itemId == R.id.secondFragment) {
                replaceFragment(new SecondFragment());
            } else if (itemId == R.id.thirdFragment) {
                replaceFragment(new ThirdFragment());
            } /*else if (itemId == R.id.tipbutton4) {
                replaceFragment(new TipFragment5());
            } /*else if (itemId == R.id.bookmark) {
                replaceFragment(new BookmarkFragment());
            } else if (itemId == R.id.history) {
                replaceFragment(new HistoryFragment());
            }*/
            return true;
        });
    }

    void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void selectBottomNavigationItem(int itemId) {
        binding.bottomNavigationView.setSelectedItemId(itemId);
    }

}
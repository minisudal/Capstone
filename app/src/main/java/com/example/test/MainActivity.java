package com.example.test;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements BottomSheetFragment1.CheckedItemsListener {

    BottomNavigationView bottomNavigationView;
    private List<String> allCheckedItems = new ArrayList<>();


    Fragment firstFragment;
    Fragment secondFragment;
    Fragment thirdFragment;
    BottomSheetFragment1 bottomSheetFragment1;
    BottomSheetFragment2 bottomSheetFragment2;
    BottomSheetFragment2 bottomSheetFragment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // 프래그먼트 초기화
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();

        // 바텀시트를 생성하고 사용자가 필요할 때 표시하도록 설정
        bottomSheetFragment1 = new BottomSheetFragment1();
        bottomSheetFragment2 = new BottomSheetFragment2();

        // 기본 프래그먼트 설정
        loadFragment(firstFragment);


        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Fragment fragment = null;

                if(itemId == R.id.firstFragment){
                    fragment = firstFragment;
                } else if(itemId == R.id.secondFragment){
                    fragment = secondFragment;
                } else if(itemId == R.id.thirdFragment){
                    fragment = thirdFragment;
                }

                return loadFragment(fragment);
            }
        });
    }
    @Override
    public void onCheckedItemsChanged(List<String> checkedItems) {
        allCheckedItems.clear();
        allCheckedItems.addAll(checkedItems);

        // 각 바텀시트에서 체크된 아이템 목록을 전달
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (fragment instanceof SecondFragment) {
            ((SecondFragment) fragment).updateCheckedItems(allCheckedItems);
        }
    }

//    @Override
//    public void onCheckedItemsChangedBottomSheet1(List<String> checkedItems) {
//        // BottomSheetFragment1에서 선택된 항목을 받아올 때의 동작을 여기에 구현
//
//    }
//
//    @Override
//    public void onCheckedItemsChangedBottomSheet2(List<String> checkedItems) {
//        // BottomSheetFragment2에서 선택된 항목을 받아올 때의 동작을 여기에 구현
//
//    }


    // 프래그먼트 전환 메서드
    boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();
            return true;
        } else {
            return false;
        }
    }
    public void selectSecondFragment() {
        bottomNavigationView.setSelectedItemId(R.id.secondFragment);
    }
//    public void showBottomSheet() {
//        bottomSheetFragment1.show(getSupportFragmentManager(), bottomSheetFragment1.getTag());
//    }

}
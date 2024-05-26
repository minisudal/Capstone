package com.example.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeMenuFragment extends Fragment {
    private static final String TAG = "HomeMenuFragment";
    private CategoryViewModel categoryViewModel;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<String> menuList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        loadRecipesAndCompare();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_menu, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        menuAdapter = new MenuAdapter(menuList);
        recyclerView.setAdapter(menuAdapter);
        return view;
    }

    private void loadRecipesAndCompare() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.recipe);  // 변경된 파일 이름에 맞춰 수정
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(inputStream);
            JsonNode recipesNode = rootNode.path("recipes");
            Set<String> addedTexts = categoryViewModel.getAddedTexts();
            Log.d(TAG, "Added texts: " + addedTexts);
            for (JsonNode recipeNode : recipesNode) {
                JsonNode ingredientsNode = recipeNode.path("ingredients");
                Set<String> ingredientNames = new HashSet<>();
                for (JsonNode ingredientNode : ingredientsNode) {
                    ingredientNames.add(ingredientNode.path("name").asText().trim());
                }
                Log.d(TAG, "Ingredient names: " + ingredientNames);
                if (addedTexts.containsAll(ingredientNames)) {
                    String menuName = recipeNode.path("menu").asText();
                    Log.d(TAG, "Adding menu: " + menuName);
                    menuList.add(menuName);
                }
            }
            // 데이터 변경 후 RecyclerView에 반영
            menuAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.e(TAG, "Error loading recipes", e);
        }
    }
}

/*
RecyclerView rv_recycle;
RecyclerView.LayoutManager layoutManager;
RecyclerView.Adapter<RecyclerAdapter.ViewHolder> adapter;
menuDTO dto;
ArrayList<menuDTO> data = new ArrayList<>();

@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home_menu, container, false);
    rv_recycle = view.findViewById(R.id.rv_recycle);
    layoutManager = new LinearLayoutManager(getContext());
    rv_recycle.setLayoutManager(layoutManager);
    adapter = new RecyclerAdapter(getContext(), data);
    rv_recycle.setAdapter(adapter);
    parser();
    return view;
}

private void parser() {
    InputStream inputStream = getResources().openRawResource(R.raw.recipe);
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    StringBuilder stringBuffer = new StringBuilder();
    String line;
    try {
        while ((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line);
        }
        // Log.v("TAG", "StringBuffer : "+ stringBuffer.toString()) ;
        JSONObject jsonObject = new JSONObject(stringBuffer.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("whole_recipe");
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String menu = jsonObject1.getString("menu");
            String ingredient = jsonObject1.getString("ingredient");
            String recipe = jsonObject1.getString("recipe");
            dto = new menuDTO(menu, ingredient, recipe);
            data.add(dto);
        }
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        try {
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
*/

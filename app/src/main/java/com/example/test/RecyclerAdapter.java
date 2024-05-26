package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import DTO.menuDTO;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context mContext;
    ArrayList<menuDTO> data;

    public RecyclerAdapter(Context mContext, ArrayList<menuDTO> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_menu.setText(data.get(position).getMenu());
        holder.tv_ingredient.setText(data.get(position).getIngredient());
        holder.tv_recipe.setText(data.get(position).getRecipe());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_menu, tv_ingredient, tv_recipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_menu = itemView.findViewById(R.id.tv_menu);
            tv_ingredient = itemView.findViewById(R.id.tv_ingredient);
            tv_recipe = itemView.findViewById(R.id.tv_recipe);



        }
    }
}

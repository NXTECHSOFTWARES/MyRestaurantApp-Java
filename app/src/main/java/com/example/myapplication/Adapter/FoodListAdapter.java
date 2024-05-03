package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Domain.Item;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyViewHolder> {

    private List<Item> myFoodList;

    public FoodListAdapter(List<Item> myItemList) {
        this.myFoodList = myItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemPrice.setText(String.format("R%s", myFoodList.get(position).getPrice()));
        holder.itemName.setText(myFoodList.get(position).getTitle());
        Picasso.get().load(myFoodList.get(position).getImagePath()).into(holder.itemImage);
        Picasso.get().load(myFoodList.get(position).getImagePath()).into(holder.largeImage);
        holder.itemCategory.setText(String.valueOf(myFoodList.get(position).getCategoryId()));
//        holder.itemImage.setImageResource(myItemList.get(position).getItemImage());
    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage, cartButton, favouriteButton, largeImage;
        TextView itemName, itemPrice, itemCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemPrice = itemView.findViewById(R.id.txtItemPrice);
            itemName = itemView.findViewById(R.id.txtItemName);
            itemImage = itemView.findViewById(R.id.itemImage);
            largeImage = itemView.findViewById(R.id.bigImage);
            itemCategory = itemView.findViewById(R.id.txtItemCategory);
            cartButton = itemView.findViewById(R.id.btnAddToCart);
            favouriteButton = itemView.findViewById(R.id.btnAddToFavourite);
        }
    }
}

package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView itemImage, cartButton, favouriteButton;
    TextView itemName, itemPrice;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        itemPrice = itemView.findViewById(R.id.txtItemPrice);
        itemName = itemView.findViewById(R.id.txtItemName);
        itemImage = itemView.findViewById(R.id.itemImage);
        cartButton = itemView.findViewById(R.id.btnAddToCart);
        favouriteButton = itemView.findViewById(R.id.btnAddToFavourite);
    }
}

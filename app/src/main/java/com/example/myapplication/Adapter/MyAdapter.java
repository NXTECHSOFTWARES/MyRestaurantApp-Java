package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Domain.Category;
import com.example.myapplication.Domain.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Item> myItemList;
    private final ArrayList<Category> categories;

    public MyAdapter(List<Item> myItemList, ArrayList<Category> categories) {
        this.myItemList = myItemList;
        this.categories = categories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemPrice.setText(String.format("R%s", myItemList.get(position).getPrice()));
        holder.itemName.setText(myItemList.get(position).getTitle());
        Picasso.get().load(myItemList.get(position).getImagePath()).into(holder.itemImage);
        if(!categories.isEmpty()) holder.itemCategory.setText(categories.get(myItemList.get(position).getCategoryId()).getTitle());
//        holder.itemImage.setImageResource(myItemList.get(position).getItemImage());
    }

    @Override
    public int getItemCount() {
        return myItemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage, cartButton, favouriteButton;
        TextView itemName, itemPrice, itemCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemPrice = itemView.findViewById(R.id.txtItemPrice);
            itemName = itemView.findViewById(R.id.txtItemName);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemCategory = itemView.findViewById(R.id.txtItemCategory);
            cartButton = itemView.findViewById(R.id.btnAddToCart);
            favouriteButton = itemView.findViewById(R.id.btnAddToFavourite);
        }
    }
}

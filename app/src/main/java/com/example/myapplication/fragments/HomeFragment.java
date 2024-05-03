package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Adapter.FoodListAdapter;
import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.Adapter.MyItem;
import com.example.myapplication.Adapter.MyViewHolder;
import com.example.myapplication.Domain.Category;
import com.example.myapplication.Domain.Item;
import com.example.myapplication.R;
import com.example.myapplication.activities.DashboardActivity;
import com.example.myapplication.activities.FoodListActivity;
import com.example.myapplication.activities.SignIn;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView popularFoodRecycleView;
    RecyclerView recommandedFoodRecycleView;
    RecyclerView categoryRecycleView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

            TextView btnViewAllPopular = rootView.findViewById(R.id.btnViewAllPopular);
            TextView btnViewAllRecommandations = rootView.findViewById(R.id.btnViewAllRecommandations);
            popularFoodRecycleView = rootView.findViewById(R.id.mostPopular);
            recommandedFoodRecycleView = rootView.findViewById(R.id.ourRecommandations);
            categoryRecycleView = rootView.findViewById(R.id.tapBarContainer);
            setCategory(categoryRecycleView);
            initMostPopularFoodItem(popularFoodRecycleView, "MostFood");
            initMostPopularFoodItem(recommandedFoodRecycleView, "OurRecommandationFood");

            btnViewAllPopular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(), FoodListActivity.class);  // Replace with your next activity class
                    startActivity(intent);
                }
            });

        return rootView;
    }
    private void initMostPopularFoodItem(RecyclerView recyclerView, String child){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        ArrayList<Item> mostPopularItemList = new ArrayList<>();
        ArrayList<Category> categoryFoodList = new ArrayList<>();
        Query query = databaseReference.child("Foods").orderByChild(child).equalTo(true);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for (DataSnapshot snap : snapshot.getChildren()){
                        Item myFood = snap.getValue(Item.class);
                        assert myFood != null;
                        int categoryId = myFood.getCategoryId();
                        mostPopularItemList.add(myFood);

                        databaseReference.child("Category").orderByChild("Id").equalTo(categoryId).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot snap: snapshot.getChildren()){
                                    Category category = snap.getValue(Category.class);
                                    assert category != null;
                                    categoryFoodList.add(category);

                                }if(!mostPopularItemList.isEmpty() && !categoryFoodList.isEmpty()){
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false) );
                                    MyAdapter itemAdapter =  new MyAdapter(mostPopularItemList, categoryFoodList);
                                    recyclerView.setAdapter(itemAdapter);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initMostPopularFoodItemList(RecyclerView recyclerView, String child){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        ArrayList<Item> mostPopularItemList = new ArrayList<>();
        ArrayList<Category> categoryFoodList = new ArrayList<>();
        Query query = databaseReference.child("Foods").orderByChild(child).equalTo(true);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for (DataSnapshot snap : snapshot.getChildren()){
                        Item myFood = snap.getValue(Item.class);
                        assert myFood != null;
                        int categoryId = myFood.getCategoryId();
                        mostPopularItemList.add(myFood);

                        databaseReference.child("Category").orderByChild("Id").equalTo(categoryId).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot snap: snapshot.getChildren()){
                                    Category category = snap.getValue(Category.class);
                                    assert category != null;
                                    categoryFoodList.add(category);

                                }if(!mostPopularItemList.isEmpty() && !categoryFoodList.isEmpty()){
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false) );
                                    FoodListAdapter itemAdapter =  new FoodListAdapter(mostPopularItemList);
                                    recyclerView.setAdapter(itemAdapter);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setCategory(RecyclerView recyclerView){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Category");
        ArrayList<Category> list = new ArrayList<>();
        Query query = reference.orderByChild("Title");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for (DataSnapshot snap : snapshot.getChildren()){
                        Category myCategory = snap.getValue(Category.class);
                        list.add(myCategory);

                    }if(!list.isEmpty()){
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false) );
                        CategoryAdapter categoryAdapter =  new CategoryAdapter(list);
                        recyclerView.setAdapter(categoryAdapter);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
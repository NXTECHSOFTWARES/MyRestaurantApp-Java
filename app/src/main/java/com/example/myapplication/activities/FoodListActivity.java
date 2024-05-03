package com.example.myapplication.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.FoodListAdapter;
import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.Domain.Category;
import com.example.myapplication.Domain.Item;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodListActivity extends AppCompatActivity {
    RecyclerView listRecycleView = findViewById(R.id.foodListContainer);
    TextView listViewName;
    ImageView backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_list);

        listViewName = findViewById(R.id.txtListName);
        backButton = findViewById(R.id.btnBackButton);
        addFoodList(listRecycleView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addFoodList(RecyclerView recyclerView) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        ArrayList<Item> categoryFoodList = new ArrayList<>();
        Query query = reference.child("Category").orderByChild("Title").equalTo("starters");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()){
                    Category category = snapshot.getValue(Category.class);
                    assert category != null;
                    int categoryId = category.getId();

                    reference.child("Foods").orderByChild("CategoryId").equalTo(categoryId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot snap : snapshot.getChildren()){
                                Item myFood = snap.getValue(Item.class);
                                categoryFoodList.add(myFood);

                            }if(!categoryFoodList.isEmpty()){
                                recyclerView.setLayoutManager(new LinearLayoutManager(FoodListActivity.this,LinearLayoutManager.HORIZONTAL, false) );
                                FoodListAdapter itemAdapter =  new FoodListAdapter(categoryFoodList);
                                recyclerView.setAdapter(itemAdapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
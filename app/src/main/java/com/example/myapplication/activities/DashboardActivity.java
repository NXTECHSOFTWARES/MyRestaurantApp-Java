package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.fragments.CartFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView  bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    CartFragment cartFragment = new CartFragment();
    NotificationsFragment notificationFragment = new NotificationsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView =  findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.home){
                    getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container, homeFragment).commit();
                    return true;
                } else if (menuItem.getItemId() == R.id.cart) {
                    getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container, cartFragment).commit();
                    return true;
                } else if (menuItem.getItemId() == R.id.notifications) {
                    getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container, notificationFragment).commit();
                    return true;
                }
                return false;
            }
        });

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();  // Get FragmentManager
        FragmentTransaction transaction = fragmentManager.beginTransaction();  // Begin a transaction

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
package com.example.myapplication.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView logoImageView = findViewById(R.id.imageView6);
        TextView textView = findViewById(R.id.appName);

        // Load animations
        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        Animation textAnimation = AnimationUtils.loadAnimation(this, R.anim.text_animation);

        // Apply animations to views
        logoImageView.startAnimation(logoAnimation);
        textView.startAnimation(textAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This code will run after 5 seconds
                Intent intent = new Intent(SplashActivity.this, SignIn.class);  // Replace with your main activity
                startActivity(intent);
                finish();
            }
        }, 2500); // Delay in milliseconds (5 seconds)
    }

    public void initLocation(){
        DatabaseReference myRef;
    }
}

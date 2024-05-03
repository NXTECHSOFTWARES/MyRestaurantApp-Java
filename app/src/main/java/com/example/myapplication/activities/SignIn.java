package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView signUpButton = findViewById(R.id.txtSignUp);
        EditText edtEmailAddress = findViewById(R.id.edtEmailAddress);
        TextView edtPassword = findViewById(R.id.edtPasswordInput);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);  // Replace with your next activity class
                startActivity(intent);
            }
        });

        Button signInButton = findViewById(R.id.btnSignUp);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailAddress, password;
                emailAddress = String.valueOf(edtEmailAddress.getText());
                password = String.valueOf(edtPassword.getText());

                if(TextUtils.isEmpty(emailAddress)){
                    Toast.makeText(SignIn.this, "enter your email", Toast.LENGTH_LONG).show();
                    return;

                }if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignIn.this, "enter your password", Toast.LENGTH_LONG).show();
                    return;

                }

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query confirmUser = reference.orderByChild("emailAddress").equalTo(emailAddress);
                confirmUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String dbPassword = snapshot.child(emailAddress.split("@")[0]).child("password").getValue(String.class);

                            if(Objects.equals(dbPassword, password)){
                                Intent intent = new Intent(SignIn.this, DashboardActivity.class);  // Replace with your next activity class
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignIn.this, "wrong email or password", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(SignIn.this, "account does not exit", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
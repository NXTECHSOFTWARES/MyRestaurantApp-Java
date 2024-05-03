package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Helper.DatabaseHelper;
import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView signUpButton = findViewById(R.id.btnSignUp);
        EditText edtFirstName = findViewById(R.id.edtFirstName);
        EditText edtLastName = findViewById(R.id.edtLastName);
        EditText edtEmailAddress = findViewById(R.id.edtEmailAddress);
        EditText edtPassword = findViewById(R.id.edtPasswordInput);
        EditText edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String emailAddress, password, firstName, lastName, confirmPassword;
                emailAddress = String.valueOf(edtEmailAddress.getText());
                firstName = String.valueOf(edtFirstName.getText());
                lastName = String.valueOf(edtLastName.getText());
                password = String.valueOf(edtPassword.getText());
                confirmPassword = String.valueOf(edtConfirmPassword.getText());


                String username = emailAddress.split("@")[0];


                if(TextUtils.isEmpty(firstName)){
                    Toast.makeText(SignUp.this, "enter your first name", Toast.LENGTH_LONG).show();
                    return;

                }if(TextUtils.isEmpty(lastName)){
                    Toast.makeText(SignUp.this, "enter your last name", Toast.LENGTH_LONG).show();
                    return;

                }if(TextUtils.isEmpty(emailAddress)){
                    Toast.makeText(SignUp.this, "enter your email address", Toast.LENGTH_LONG).show();
                    return;

                }if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUp.this, "enter a password", Toast.LENGTH_LONG).show();
                    return;

                }if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(SignUp.this, "confirm password your password", Toast.LENGTH_LONG).show();
                    return;

                }if(!(password.equals(confirmPassword))){
                    Toast.makeText(SignUp.this, "password did not match", Toast.LENGTH_LONG).show();
                    return;
                }

                DatabaseHelper newUser = new DatabaseHelper(firstName, lastName, emailAddress, password);
                reference.child(username).setValue(newUser);
                Toast.makeText(SignUp.this, "successfully signed up!", Toast.LENGTH_LONG).show();



                Intent intent = new Intent(SignUp.this, SignIn.class);  // Replace with your next activity class
                startActivity(intent);
            }
        });

    }
}
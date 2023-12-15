package com.example.onlin_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_up extends AppCompatActivity {
    TextInputEditText textInputEditEmail, textInputEditPassword,textInputRepassword;
    Button signupbnt;
    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        textInputEditEmail = findViewById(R.id.txt_input_email);
        textInputEditPassword = findViewById(R.id.input_password);
        textInputRepassword = findViewById(R.id.input_re_password);
        signupbnt = findViewById(R.id.signupbnt);

        signupbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(textInputEditEmail.getText());
                String password = String.valueOf(textInputEditPassword.getText());
                String repassword = String.valueOf(textInputRepassword.getText());

                if (TextUtils.isEmpty(email)){
                    textInputEditEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    textInputEditPassword.setError("Password is required.");
                    return;
                }
                if (password.length() <6){
                    textInputEditPassword.setError("Password must be > 6 charater.");
                    return;
                }
                if (password == repassword){
                    textInputEditPassword.setError("repassword must be same password.");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Sign_up.this, "User created",Toast.LENGTH_SHORT);
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Sign_up.this,"Error! "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
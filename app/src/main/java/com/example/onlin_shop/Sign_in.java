package com.example.onlin_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Sign_in extends AppCompatActivity {
    TextInputEditText textInputEditemail, textInputEditpassword;
    TextView Eronemail,Eronpass;
    Button signinbnt,signupbnt;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        textInputEditemail = findViewById(R.id.txt_input_email);
        textInputEditpassword = findViewById(R.id.input_password);
        Eronemail = findViewById(R.id.Eronemail_view);
        Eronpass = findViewById(R.id.Eronpass_view);
        signinbnt = findViewById(R.id.signinbnt);
        signupbnt = findViewById(R.id.signupbnt);

        signupbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Sign_up.class));
                finishAffinity();
            }
        });
        signinbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                String erontext="";
                email = String.valueOf(textInputEditemail.getText());
                password = String.valueOf(textInputEditpassword.getText());

                if(TextUtils.isEmpty(email)){
                    textInputEditemail.setError("email can't empty");
                    Eronemail.setText("email can't empty");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    textInputEditpassword.setError("password can't empty");
                    Eronpass.setText("password can't empty");
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Sign_in.this,"sign in accout", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finishAffinity();
                        }else {
                            Toast.makeText(Sign_in.this,"sign in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
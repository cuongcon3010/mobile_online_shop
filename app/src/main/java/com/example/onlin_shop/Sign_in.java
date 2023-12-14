package com.example.onlin_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    TextView Eron;
    Button signinbnt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        textInputEditemail = findViewById(R.id.txt_input_email);
        textInputEditpassword = findViewById(R.id.input_password);
        Eron = findViewById(R.id.Eron_view);
        signinbnt = findViewById(R.id.signinbnt);

        signinbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                String erontext="";
                email = String.valueOf(textInputEditemail.getText());
                password = String.valueOf(textInputEditpassword.getText());

                if(TextUtils.isEmpty(email)){
                    erontext += "enter your email";
                }
                if(TextUtils.isEmpty(password)){
                    if (TextUtils.isEmpty(erontext)){
                        erontext += "enter your password";
                    }else {
                        erontext += "\nenter your password";
                    }
                    Eron.setText(erontext);
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Sign_in.this,"sign in accout", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Sign_in.this,"sign in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
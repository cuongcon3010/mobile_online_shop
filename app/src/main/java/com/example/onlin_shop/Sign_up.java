package com.example.onlin_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class Sign_up extends AppCompatActivity {
    TextInputEditText textInputEditemail, textInputEditpassword,textInputRepassword;
    Button signupbnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
package com.example.onlin_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditAcount extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    ImageFilterButton avata;
    TextInputEditText SDT,Name,Pass;
    Button complete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_acount);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        avata = findViewById(R.id.avata);
        SDT = findViewById(R.id.txt_input_SDT);
        Name = findViewById(R.id.txt_input_name);
        Pass = findViewById(R.id.txt_input_password);
        complete = findViewById(R.id.edit_account);

        SDT.setText(user.getPhoneNumber());
        Name.setText(user.getDisplayName());
    }
}
package com.example.onlin_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String UID;
    FirebaseAuth mAuth;
    FirebaseUser user;
    EditText search;
    ImageButton logoutbnt;
    ImageFilterButton avata;

    ListView listView;

    ArrayList<Item> items = new ArrayList<>();
    CustomAdapter adapter; // Moved the adapter declaration to the class level.

    boolean isLoading = false;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        UID = mAuth.getUid();

        logoutbnt = findViewById(R.id.logoutbnt);
        search = findViewById(R.id.search_box);
        avata = findViewById(R.id.avata);
        listView = findViewById(R.id.recyclerView);

        addlist();

        getImage(UID, avata);

        avata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EditAcount.class));
            }
        });

        logoutbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), Sign_in.class));
                finishAffinity();
            }
        });

        // Initialize the adapter
        adapter = new CustomAdapter(this, R.layout.itemview, items);
        listView.setAdapter(adapter);

    }

    private void getImage(String image, ImageView img) {
        StorageReference storageRef = storage.getReferenceFromUrl("gs://online-shop-a9b3b.appspot.com/UserAvata");
        storageRef.child(image + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                loadImageUsingGlide(String.valueOf(uri), img);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    private void loadImageUsingGlide(String imageUrl, ImageView img) {
        // Use Glide to load and display the image
        Glide.with(this)
                .load(imageUrl)
                .into(img);
    }

    private void getdata(String url, Item item){

        DatabaseReference data = database.getReference();
        data.child("items/item"+url+"/name_item").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                item.setName_item(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        data.child("items/item"+url+"/gia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                item.setGia(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        data.child("items/item"+url+"/nguoi_ban").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                item.setNguoi_ban(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void addlist(){
        for (int i = 1;i<=5;i++){
            Item item =null;
            getdata(String.valueOf(i),item);
            items.add(item);
        }
    }
}

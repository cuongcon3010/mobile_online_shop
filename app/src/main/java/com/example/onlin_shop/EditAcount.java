package com.example.onlin_shop;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;


public class EditAcount extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    String UID;
    ImageView avata;
    TextInputEditText SDT, Name, Address;
    Button complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_acount);

        mAuth = FirebaseAuth.getInstance();
        UID = mAuth.getUid();
        database = FirebaseDatabase.getInstance("https://online-shop-a9b3b-default-rtdb.asia-southeast1.firebasedatabase.app/");

        avata = findViewById(R.id.avata);
        SDT = findViewById(R.id.txt_input_SDT);
        Name = findViewById(R.id.txt_input_Name);
        Address = findViewById(R.id.txt_input_address);
        complete = findViewById(R.id.edit_account);

        getImage("gs://online-shop-a9b3b.appspot.com/UserAvata");

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushdata();
                pushIMGfirebase();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();

            }
        });
        avata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                pickImageActivityResulttLaucher.launch(intent);

            }
        });

        getdata("Users/" + UID + "/Name",Name);
        getdata("Users/" + UID + "/STD",SDT);
        getdata("Users/" + UID + "/address",Address);



    }


    private void pushdata() {

        DatabaseReference nameDB = database.getReference("Users/" + UID + "/Name");
        nameDB.setValue(String.valueOf(Name.getText()));

        DatabaseReference stdDB = database.getReference("Users/" + UID + "/STD");
        stdDB.setValue(String.valueOf(SDT.getText()));

        DatabaseReference addressDB = database.getReference("Users/" + UID + "/address");
        addressDB.setValue(String.valueOf(Address.getText()));
    }
    ////////////////////////////////////       Image          //////////////////////////////////////
    //lấy URI ảnh từ thư viện
    String getPathFromURI(Uri contenturi) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contenturi, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    //chọn ảnh từ thư viện

    ActivityResultLauncher<Intent> pickImageActivityResulttLaucher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri selectedImageUri = data.getData();

                        final String path = getPathFromURI(selectedImageUri);
                        if (path != null) {
                            File f = new File(path);
                            selectedImageUri = Uri.fromFile(f);
                        }
                        avata.setImageURI(selectedImageUri);
                    }
                }
            });
    //đẩy ảnh lên firebasestoge

    private void pushIMGfirebase(){
        StorageReference storageReference = storage.getReferenceFromUrl("gs://online-shop-a9b3b.appspot.com").child("UserAvata");
        StorageReference mountainsRef = storageReference.child(UID+".png");

        avata.setDrawingCacheEnabled(true);
        avata.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) avata.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        });
    }

    ///////////// load ảnh từ firebase ////////////////
    private void getImage(String url){
        StorageReference storageRef = storage.getReferenceFromUrl(url);
        storageRef.child(UID+".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                loadImageUsingGlide(String.valueOf(uri));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
    private void loadImageUsingGlide(String imageUrl) {
        // Sử dụng Glide để tải và hiển thị ảnh
        Glide.with(this)
                .load(imageUrl)
                .into(avata);
    }

    // lay dữ liệu sring về
    private void getdata(String url, TextView showtext){
        DatabaseReference data = database.getReference();
        data.child(url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                showtext.setText(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
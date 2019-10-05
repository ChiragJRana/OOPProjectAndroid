package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class Register_Organiser extends AppCompatActivity {

    private EditText com_name;
    private EditText com_email;
    private EditText com_pass;
    private FirebaseAuth firebaseAuth_com_email;
    private FirebaseStorage firebaseStorage_com_storage;
    private FirebaseDatabase firebaseDatabase_com_data;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser_com;
    private ImageView imageView_com_img;
    private Button add_com;
    Uri imagePath;
    private static int PICK_IMAGE=123;
    ArrayList<Event_Profile> list;
    RecyclerView recyclerView;
    C_Adapter c_adapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData()!=null){
            imagePath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                imageView_com_img.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitser_organiser);
        getId();

        firebaseAuth_com_email = FirebaseAuth.getInstance();
        firebaseDatabase_com_data = FirebaseDatabase.getInstance();
        firebaseStorage_com_storage = FirebaseStorage.getInstance();

        databaseReference = firebaseDatabase_com_data.getReference();
        storageReference = firebaseStorage_com_storage.getReference();

        add_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_email,user_pass,user_name;
                user_email = com_email.getText().toString().trim();
                user_pass = com_pass.getText().toString().trim();
                user_name = com_name.getText().toString();
                if(checke(user_email) && checkp(user_pass) && checkn(user_name) && checkimage()){
                    //Use these two string in authentication...
                    firebaseAuth_com_email.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendVerificationEmail();
                                Toast.makeText(Register_Organiser.this, "Success...Verification email send to your email account...", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(Register_Organiser.this, "Failed...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


                /*Intent intent = new Intent(Register_Organiser.this,See_Organiser.class);
                startActivity(intent);*/
            }
        });

        imageView_com_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
            }
        });
    }

    private void getId(){
        com_pass = findViewById(R.id.et_usr_pass);
        com_email = findViewById(R.id.et_usr_email);
        com_name = findViewById(R.id.com_name);
        imageView_com_img = findViewById(R.id.com_img);
        add_com = findViewById(R.id.btn_usr_register);
    }

    private boolean checke(String user_email){
        if(user_email.contains("@gmail.com")){
            return true;
        }
        else{
            com_email.setError("Error");
            return  false;
        }
    }
    private boolean checkp(String user_pass){
        if(user_pass.isEmpty()){
            com_pass.setError("Error");
            return false;
        }
        else{
            return true;
        }
    }
    private boolean checkimage(){
        if(imagePath==null){
            Toast.makeText(this, "Please select the required image...", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }

    }
    private boolean checkn(String user_name){
        if(user_name.isEmpty()){
            com_name.setError("Error");
            return false;
        }
        else{
            return true;
        }
    }

    //Verification email function...
    private void sendVerificationEmail(){
        FirebaseUser firebaseUser = firebaseAuth_com_email.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        uploadUserData();
                        firebaseAuth_com_email.signOut();
                        Intent intent = new Intent(Register_Organiser.this,See_Organiser.class);
                    }
                    else{
                        Toast.makeText(Register_Organiser.this, "Could not upload the data", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void uploadUserData(){
        firebaseUser_com = firebaseAuth_com_email.getCurrentUser();
        StorageReference imageReference = storageReference.child("Committee Images").child(firebaseUser_com.getUid());
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register_Organiser.this, "Could not upload the image...", Toast.LENGTH_SHORT).show();
            }
        });
        Organiser_Profile organiser_profile = new Organiser_Profile(com_name.getText().toString(),com_email.getText().toString());
        databaseReference.child("Organisers").child(firebaseUser_com.getUid()).setValue(organiser_profile);
    }
}

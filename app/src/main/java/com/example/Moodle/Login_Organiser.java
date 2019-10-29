package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Login_Organiser extends AppCompatActivity {

    private EditText org_email,org_pass;
    private Button org_register,org_login;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__organiser);

        getUi();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            finish();
            startActivity(new Intent(Login_Organiser.this,Organiser_Home_page.class));
        }

        progressDialog = new ProgressDialog(this);

        org_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    validate(org_email.getText().toString(),org_pass.getText().toString());
                }
            }
        });

        org_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void getUi(){
        org_email = findViewById(R.id.org_email);
        org_pass = findViewById(R.id.org_pass);
        org_login = findViewById(R.id.org_btn_login);
        org_register = findViewById(R.id.org_btn_register);
    }

    private boolean check() {
        boolean result = false;
        String userName = org_email.getText().toString();
        String userPassword = org_pass.getText().toString();
        if (userName.isEmpty()) {
            Toast.makeText(this, "Please enter the user name", Toast.LENGTH_SHORT).show();
            return result;
        }
        if (userPassword.isEmpty()) {
            Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
            return result;
        }
        result = true;
        return result;
    }

    private void validate(String email,String password){
        progressDialog.setMessage("Please wait while the verification is done...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    checkEmailVerification();
                    startActivity(new Intent(Login_Organiser.this, Organiser_Home_page.class));
                    finish();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(Login_Organiser.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkEmailVerification(){
        firebaseUser = firebaseAuth.getCurrentUser();
        Boolean check = firebaseUser.isEmailVerified();
        if(check){
            startActivity(new Intent(Login_Organiser.this,Organiser_Home_page.class));
            finish();
        }else{
            Toast.makeText(this, "Please verify your email first...", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}

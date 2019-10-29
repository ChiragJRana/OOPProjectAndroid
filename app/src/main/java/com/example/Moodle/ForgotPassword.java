package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Moodle.Student.Login_Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgotPassword extends AppCompatActivity {

    private EditText Email;
    private Button Reset_Pass;
    private FirebaseAuth firebaseAuth;

    public void toaster(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setUi();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Reset_Pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = Email.getText().toString().trim();
                //Checking if the string is empty or not...
                if(userEmail.equals("")){
                    toaster("Please enter the email id...");
                }else{
                    firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                toaster("Password Reset Email sent Successful...");
                                //finish();
                                startActivity(new Intent(ForgotPassword.this, Login_Student.class));
                            }else{
                                toaster("Password Reset Unsuccessful try again");
                            }
                        }
                    });
                }
            }
        });
    }
    private void setUi(){
        Email = (EditText) findViewById(R.id.et_email_forgot_pass);
        Reset_Pass = (Button)findViewById(R.id.btn_reset_pass);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

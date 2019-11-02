package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ChangePassword extends AppCompatActivity {

    private EditText newPass,retpyePass;
    private Button changePass;
    private FirebaseUser firebaseUser;
    String newpassstring;
    String retypepassstring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        setUi();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

            changePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    retypepassstring = retpyePass.getText().toString();
                    newpassstring = newPass.getText().toString();

                    if(verified()){
                    firebaseUser.updatePassword(retypepassstring).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePassword.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                //finish();
                            } else {
                                Toast.makeText(ChangePassword.this, "Unsuccessful...", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                }
            });
    }

    private void setUi(){
        newPass = (EditText)findViewById(R.id.etnewpass);
        retpyePass = (EditText)findViewById(R.id.etretypepass);
        changePass = (Button)findViewById(R.id.btnchangepass);
    }
    public boolean verified(){
        if(newpassstring.length() < 10){
            newPass.setError("Short");
        }
        if(newpassstring.isEmpty()){
            newPass.setError("Error");
            return false;
        }
        if(retypepassstring.isEmpty()){
            retpyePass.setError("Empty");
            return false;
        }
        if(!newpassstring.equals(retypepassstring)){
            retpyePass.setError("Unmatched");
            return false;
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
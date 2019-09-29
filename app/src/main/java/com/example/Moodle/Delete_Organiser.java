package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Delete_Organiser extends AppCompatActivity {
    private EditText com_name,com_email,com_pass;
    private Button auth,delete;
    private Boolean getAuth=false;


    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__organiser);
        getUi();
        delete.setEnabled(false);

        //Getting all instances of firebase...
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        //Getting the required references...

        storageReference = firebaseStorage.getReference();


        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public  void  onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Organiser_Profile organiser_profile = dataSnapshot.getValue(Organiser_Profile.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    String com_e = com_email.getText().toString();
                    String com_p = com_pass.getText().toString();
                    validate(com_e,com_p);
                }
                else{
                    Toast.makeText(Delete_Organiser.this, "Please enter the required credentials...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!getAuth){
                    delete.setEnabled(false);
                }
                else{
                    delete.setEnabled(true);
                }
            }
        });
    }
    private void getUi(){
        com_name = findViewById(R.id.com_name);
        com_email = findViewById(R.id.com_email);
        com_pass = findViewById(R.id.com_pass);
        auth = findViewById(R.id.btn_delete_auth);
        delete = findViewById(R.id.btn_delete_com);
    }
    private void validate(String come,String comp){
        firebaseAuth.signInWithEmailAndPassword(come,comp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
                    Toast.makeText(Delete_Organiser.this, "Successfully Deleted...", Toast.LENGTH_SHORT).show();
                    delete.setEnabled(true);
                }
                else{
                    Toast.makeText(Delete_Organiser.this, "Please try again...", Toast.LENGTH_SHORT).show();
                    delete.setEnabled(false);
                }
            }
        });
    }
    private boolean check(){
        String com_n = com_name.getText().toString();
        String com_e = com_email.getText().toString();
        String com_p = com_pass.getText().toString();
        if(com_e.isEmpty()){
            //com_email.setError("Error");
            return false;
        }
        else if(com_p.isEmpty()){
            //com_pass.setError("Error");
            return false;
        }
        else if(com_n.isEmpty()){
            //com_name.setError("Error");
            return false;
        }
        else{
            return true;
        }
    }
}

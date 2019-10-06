package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Add_New_Event extends AppCompatActivity {

    private EditText event_name,event_date,ctc_no;
    private Button add_event;

    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__event);

        getUi();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        databaseReference = firebaseDatabase.getReference();
        storageReference = firebaseStorage.getReference();

        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadEventDetails();
            }
        });

    }

    private void uploadEventDetails(){
        firebaseUser = firebaseAuth.getCurrentUser();
        Event_Details event_details = new Event_Details(event_name.getText().toString(),event_date.getText().toString(),ctc_no.getText().toString());
        databaseReference.child("Events").child(firebaseUser.getUid()).child(event_name.getText().toString()).setValue(event_details).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Add_New_Event.this, "Successfully added event...", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Add_New_Event.this,Organiser_Home_page.class));
                    finish();
                }else{
                    Toast.makeText(Add_New_Event.this, "Unsuccessful to upload event...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getUi(){
        event_name = findViewById(R.id.et_event_name);
        event_date = findViewById(R.id.et_event_date);
        ctc_no = findViewById(R.id.et_ctc_no);
        add_event = findViewById(R.id.btn_add_event);
    }
}

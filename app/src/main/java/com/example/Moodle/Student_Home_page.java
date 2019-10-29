package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Student_Home_page extends AppCompatActivity {

    private List<Event_Details> ed;
    private Event_Adapter event_adapter;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private FirebaseUser firebaseUser;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();
        databaseReference = firebaseDatabase.getReference();

        getUi();
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        init();

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ed = new ArrayList<>();
                for(DataSnapshot dataSnapshot1:dataSnapshot.child("Events").getChildren()){
                        Event_Details event_details = dataSnapshot1.getValue(Event_Details.class);
                        event_details.setCom_name(event_details.getCom_name());
                        event_details.setEvent_name(event_details.getEvent_name());    //I want to set the event name but i would have to get it from the object only...
                        event_details.setEvent_date(event_details.getEvent_date());
                        event_details.setCtc_no(event_details.getCtc_no());
                        ed.add(event_details);
                    Toast.makeText(Student_Home_page.this, "Success...", Toast.LENGTH_SHORT).show();
                }

                    event_adapter.setData(ed);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void Logout(){
        firebaseAuth.signOut();
        startActivity(new Intent(Student_Home_page.this, App_Home_Page.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {                                                 //Overriden method...
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
                break;
            }
            case R.id.profileMenu:{
                startActivity(new Intent(Student_Home_page.this,ProfileActivity.class));
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void getUi(){
        recyclerView = findViewById(R.id.rv_event_list);
    }

    private void init(){
        event_adapter = new Event_Adapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(event_adapter);
    }
}

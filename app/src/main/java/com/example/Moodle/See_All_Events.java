package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class See_All_Events extends AppCompatActivity /*implements Event_Adapter.OnNoteListner*/{

    private List<Event_Details> event_details;

    private RecyclerView recyclerView;
    private Event_Adapter event_adapter;
    private static final String TAG="";
    private ImageView profilepic;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private FirebaseUser firebaseUser;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_organiser);
        getId();

        init();

        //getManualData();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();
        databaseReference = firebaseDatabase.getReference();

    }

    private void getId(){
        recyclerView = findViewById(R.id.rv_event_list);
    }
    private void init(){
        //event_adapter = new Event_Adapter(this,com_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(event_adapter);
    }
    /*private void getManualData(){
        for(int i=0;i<1;i++){
            Event_Profile com_name =new Event_Profile();
            switch(i){
                case 0:
                    com_name.setCommit_name("CSI");
                    break;
                *//*case 1:
                    com_name.setCommit_name("FACE");
                    break;
                case 2:
                    com_name.setCommit_name("IEEE");
                    break;
                case 3:
                    com_name.setCommit_name("WIE");
                    break;
                case 4:
                    com_name.setCommit_name("Coding club");
                    break;
                case 5:
                    com_name.setCommit_name("SPORTS");
                    break;
                case 6:
                    com_name.setCommit_name("ROBOT");
                    break;
                case 7:
                    com_name.setCommit_name("Student Council");
                    break;
                case 8:
                    com_name.setCommit_name(" ");
                    break;
                case 9:
                    com_name.setCommit_name(" ");
                    break;*//*
            }
            com_list.add(com_name);
        }
        event_adapter.setData(com_list);
    }*/


    /*@Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, Register_Organiser.class);
        startActivity(intent);
    }*/

}

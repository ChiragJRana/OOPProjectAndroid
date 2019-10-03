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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class See_Organiser extends AppCompatActivity implements C_Adapter.OnNoteListner{

    private List<Commitee_Profile> com_list;

    private RecyclerView recyclerView;
    private C_Adapter c_adapter;
    private static final String TAG="";
    private Button inset_btn;
    private Button del_btn;
    private Button go;
    private String cn;
    private ImageView profilepic;
    private TextView com_name;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_organiser);
        getId();
        init();


        //firebaseUser = firebaseAuth.getCurrentUser();
        //final String currentUserId = firebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //getManualData();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        //DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference storageReference = firebaseStorage.getReference();

        /*databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                com_list = new ArrayList<>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    firebaseUser = firebaseAuth.getCurrentUser();
                    Commitee_Profile commitee_profile = dataSnapshot1.child("Organisers").child(firebaseUser.getUid()).getValue(Commitee_Profile.class);
                    com_name.setText(commitee_profile.getCommit_name());
                    com_list.add(commitee_profile);
                }
                c_adapter.setData(com_list);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        inset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(See_Organiser.this,Register_Organiser.class);
                startActivity(intent);
                //insertItem();
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(See_Organiser.this,Delete_Organiser.class);
                startActivity(intent);
            }
        });

        /*go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(See_Organiser.this,Verify_Orgniser.class);
                startActivity(intent);
            }
        });*/

    }

    public void insertItem(){
        //getDynamicData();
        /*Intent intentCaller = getIntent();
        String cn = intentCaller.getStringExtra("CN");
        String ce = intentCaller.getStringExtra("CE");
        com_list.add(new Commitee_Profile(cn));*/
    }
    public void deleteItem(int position){

    }

    private void getId(){
        recyclerView = findViewById(R.id.rv_com_list);
        inset_btn = findViewById(R.id.btn_insert);
        del_btn = findViewById(R.id.btn_del);
        com_name = findViewById(R.id.com_name);
        //go = findViewById(R.id.btn_org_detail);
    }
    private void init(){
        c_adapter = new C_Adapter(this,com_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(c_adapter);
    }
    /*private void getManualData(){
        for(int i=0;i<1;i++){
            Commitee_Profile com_name =new Commitee_Profile();
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
        c_adapter.setData(com_list);
    }*/

    /*private void getDynamicData(){
        Commitee_Profile commitiesname = new Commitee_Profile("");
        commitiesname.setCommit_name(cn);
        //commitiesname.setCommit_img(Picasso.get().load(uri));
    }*/


    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, Register_Organiser.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*Firebasere<Organiser_Profile,> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Organiser_Profile,>
                (Organiser_Profile.class,R.layout.single_organiser_layout,firebaseDatabase){
            @Override
            protected void populateViewHolder(){

               }
        };*/

    }
}

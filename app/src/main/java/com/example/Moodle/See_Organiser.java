package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Organisers");
        //getManualData();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        //DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference storageReference = firebaseStorage.getReference();

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
        go = findViewById(R.id.btn_org_detail);
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
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                com_list = new ArrayList<Commitee_Profile>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    /*firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    try{
                        final String userId = firebaseUser.getUid();
                        if(dataSnapshot.getKey().equals(userId)){
                            Commitee_Profile commitee_profile = dataSnapshot1.getValue(Commitee_Profile.class);
                            com_list.add(commitee_profile);
                        }
                    }catch(NullPointerException e){
                        e.printStackTrace();
                    }*/

                    /*firebaseUser = firebaseAuth.getCurrentUser();
                    int uid = Integer.parseInt(firebaseUser.getUid());*/
                    //Commitee_Profile commitee_profile =  new Commitee_Profile();
                    Commitee_Profile commitee_profile = dataSnapshot1.getValue(Commitee_Profile.class);
                    //String name = commitee_profile.getCommit_name();
                    //commitee_profile.setCommit_name(name);
                    com_list.add(commitee_profile);
                    /*String name = commitee_profile.getCommit_name();
                    commitee_profile.setCommit_name(name);*/
                    //commitee_profile.getCommit_img();

                }
                c_adapter.setData(com_list);
                /*c_adapter = new C_Adapter(See_Organiser.this,com_list);
                recyclerView.setAdapter(c_adapter);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

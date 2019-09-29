package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class See_Organiser extends AppCompatActivity implements C_Adapter.OnNoteListner{

    private List<Commitee_Profile> com_list;

    private RecyclerView recyclerView;
    private C_Adapter c_adapter;
    private static final String TAG="";
    private Button inset_btn;
    private Button del_btn;
    private String cn;
    private ImageView profilepic;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__organiser);
        getId();
        init();
        com_list = new ArrayList<>();


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



    }

    public void insertItem(){
        getDynamicData();
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
    }
    private void init(){
        c_adapter = new C_Adapter(this,this);
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

    private void getDynamicData(){
        Commitee_Profile commitiesname = new Commitee_Profile("");
        commitiesname.setCommit_name(cn);
        //commitiesname.setCommit_img(Picasso.get().load(uri));
    }


    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, Register_Organiser.class);
        startActivity(intent);
    }
}

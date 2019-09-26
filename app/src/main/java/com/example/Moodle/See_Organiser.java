package com.example.Moodle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class See_Organiser extends AppCompatActivity implements C_Adapter.OnNoteListner{
    private RecyclerView recyclerView;
    private C_Adapter c_adapter;
    private static final String TAG="";
    private EditText inset_posi;
    private EditText del_posi;
    private Button inset_btn;
    private Button del_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__organiser);
        getId();
        init();
        getManualData();

        inset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(See_Organiser.this,Register_Organiser.class);
                startActivity(intent);
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

    public void insertItem(int position){

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
    private void getManualData(){
        List<Commitiesname> com_list = new ArrayList<>(10);
        for(int i=0;i<1;i++){
            Commitiesname com_name =new Commitiesname();
            switch(i){
                case 0:
                    com_name.setCommit_name("CSI");
                    break;
                /*case 1:
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
                    break;*/
            }
            com_list.add(com_name);
        }
        c_adapter.setData(com_list);
    }


    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, Register_Organiser.class);
        startActivity(intent);
    }
}

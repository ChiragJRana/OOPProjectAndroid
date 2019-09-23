package com.example.Moodle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Register_Organiser extends AppCompatActivity{
    private RecyclerView recyclerView;
    private C_Adapter c_adapter;
    private static final String TAG="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__organiser);
        getId();
        init();
        getManualData();
    }

    private void getId(){
        recyclerView = findViewById(R.id.rv_com_list);
    }
    private void init(){
        c_adapter = new C_Adapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(c_adapter);
    }
    private void getManualData(){
        List<Commitiesname> com_list = new ArrayList<>(10);
        for(int i=0;i<10;i++){
            Commitiesname com_name =new Commitiesname();
            switch(i){
                case 0:
                    com_name.setCommit_name("CSI");
                    break;
                case 1:
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
                    break;
            }
            com_list.add(com_name);
        }
        c_adapter.setData(com_list);
    }
}

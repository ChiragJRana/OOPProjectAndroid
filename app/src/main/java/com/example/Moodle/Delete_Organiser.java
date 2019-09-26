package com.example.Moodle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Delete_Organiser extends AppCompatActivity {
    private EditText com_name,com_email,com_pass;
    private Button auth,delete;
    private Boolean getAuth=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__organiser);
        getUi();
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getAuth==false){
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
    }

}

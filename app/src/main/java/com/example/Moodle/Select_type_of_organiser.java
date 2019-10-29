package com.example.Moodle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Select_type_of_organiser extends AppCompatActivity {

    private Button new_org,old_org;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_of_organiser);
        getUi();

        new_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_type_of_organiser.this,Register_Organiser.class);
                startActivity(intent);
            }
        });

        old_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_type_of_organiser.this,Login_Organiser.class);
                startActivity(intent);
            }
        });
    }


    private void getUi(){
        new_org = (Button)findViewById(R.id.new_org);
        old_org = (Button)findViewById(R.id.old_org);
    }
}

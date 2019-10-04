package com.example.Moodle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class App_Home_Page extends AppCompatActivity {
    private Button btn_student;
    private Button btn_organiser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app__home__page);
        getUi();
        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(App_Home_Page.this, Login_Student.class);
                startActivity(intent);
            }
        });
        btn_organiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(App_Home_Page.this,Select_type_of_organiser.class);
                startActivity(intent);
            }
        });
    }
    private void getUi(){
        btn_student = findViewById(R.id.btn_student);
        btn_organiser = findViewById(R.id.btn_organiser);
    }
}

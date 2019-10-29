package com.example.Moodle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.Moodle.Student.Login_Student;


public class App_Home_Page extends AppCompatActivity {
    private Button btn_student;
    private Button btn_organiser;
    private ImageView logoimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);              //Asked to remove the title bar..
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app__home__page);

        getSupportActionBar().hide();
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
        logoimage = findViewById(R.id.splogohomepage);
        logoimage.setTranslationX(1000f);
        btn_organiser.setTranslationX(-1000f);
        btn_student.setTranslationX(-1000f);
        logoimage.animate().translationXBy(-1000f).setDuration(1000);
        btn_organiser.animate().translationXBy(1000f).setDuration(1000);
        btn_student.animate().translationXBy(1000f).setDuration(1000);
    }
}
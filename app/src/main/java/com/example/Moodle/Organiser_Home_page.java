package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Organiser_Home_page extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button add_event;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organiser__home_page);

        getUi();
        firebaseAuth = FirebaseAuth.getInstance();

        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Organiser_Home_page.this,Add_New_Event.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
    }

    private void Logout(){
        firebaseAuth.signOut();
        startActivity(new Intent(Organiser_Home_page.this,App_Home_Page.class));
        finish();
    }



    private void getUi(){
        add_event = findViewById(R.id.add_event);
        logout = findViewById(R.id.btn_logout);
    }
}

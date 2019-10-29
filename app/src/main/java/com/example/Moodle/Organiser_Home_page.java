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
    }

    private void Logout(){
        firebaseAuth.signOut();
        startActivity(new Intent(Organiser_Home_page.this,App_Home_Page.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_organiser,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu_Org:{
                Logout();
                break;
            }
            case R.id.profileMenu_Org:{
                startActivity(new Intent(Organiser_Home_page.this,Organiser_Profile_Page.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUi(){
        add_event = findViewById(R.id.add_event);
    }
}

package com.example.Moodle;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Organiser_Profile_Page extends AppCompatActivity {

    private ImageView com_pro_pic;
    private EditText com_name,com_email;
    private Button edit_data,change_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organiser__home_page);

        getUi();
    }

    private void getUi(){
        com_pro_pic = findViewById(R.id.pro_pic_com);
        com_name = findViewById(R.id.et_com_name);
        com_email = findViewById(R.id.et_com_email);
        edit_data = findViewById(R.id.btn_edit_data);
        change_pass = findViewById(R.id.btn_change_pass);
    }
}

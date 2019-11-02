package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
public class Add_New_Event extends AppCompatActivity {

    private EditText com_name,event_name,ctc_no;
    private TextView event_date,event_time;
    private Button add_event;
    private DatePickerDialog datepickerdialog ;
    private TimePickerDialog timePickerDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private int year,month,dayofMonth,hour,minute;
    private String ampm;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__event);

        getUi();//
        event_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datepickerdialog = new DatePickerDialog(Add_New_Event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        event_date.setText(day +"/"+ (month+1)+"/"+year );

                    }

                }, year, month,dayofMonth);
                datepickerdialog.show();
            }
        });
        event_time.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(Add_New_Event.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourpicked, int minuteicked) {
                        if(hourpicked >= 12){
                            ampm = "PM";
                        }else{
                            ampm = "AM";
                        }
                        event_time.setText(String.format("%02d:%02d",hourpicked,minuteicked)+ampm);
//                        event_time.setText(hourpicked + " : "+minuteicked);
                    }
                },hour,minute,false);
                timePickerDialog.show();
            }
        });
//
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        databaseReference = firebaseDatabase.getReference();
        storageReference = firebaseStorage.getReference();

        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verify()){
                    uploadEventDetails();

                }
            }
        });
    }

    private void uploadEventDetails(){
        //firebaseUser = firebaseAuth.getCurrentUser();
        Event_Details event_details = new Event_Details(com_name.getText().toString(),event_name.getText().toString(),event_date.getText().toString(),event_time.getText().toString(),ctc_no.getText().toString());
        databaseReference.child("Events")/*.child(firebaseUser.getUid())*/.child(event_name.getText().toString()).setValue(event_details).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Add_New_Event.this, "Successfully added event...", Toast.LENGTH_LONG).show();
                     finish();
                }else{
                    Toast.makeText(Add_New_Event.this, "Unsuccessful to upload event...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getUi(){
        com_name = findViewById(R.id.et_com_name);
        event_name = findViewById(R.id.et_event_name);
        event_date = findViewById(R.id.et_event_date);
        event_time = findViewById(R.id.et_event_time);
        ctc_no = findViewById(R.id.et_ctc_no);
        add_event = findViewById(R.id.btn_add_event);
     }
    private boolean verify(){
        if(com_name.getText().toString().isEmpty()){
            com_name.setError("empty");
            return false;
        }else if (event_name.getText().toString().isEmpty()){
            event_name.setError("empty");
            return false;
        }else if (event_date.getText().toString().isEmpty()){
            event_date.setError("empty");
            return false;
        }else if (event_time.getText().toString().isEmpty()){
            event_time.setError("empty");
            return false;}
        else if (ctc_no.getText().toString().isEmpty()){
            ctc_no.setError("empty");
            return false;
        }
        if (ctc_no.getText().toString().length() != 10) {
            ctc_no.setError("Invalid");
            Toast.makeText(Add_New_Event.this, "Incorrect Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
}
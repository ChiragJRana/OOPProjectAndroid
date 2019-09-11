package com.example.splash_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.lang.UCharacterEnums;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class UpdateUserInfo extends AppCompatActivity {

    private ImageView uppropicImg;
    private EditText upFn,upLn,upGen,upEmail,upStd,upDob;
    private Button upSave;
    private FirebaseDatabase profileFirebaseDatabase;
    private FirebaseAuth profileFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        setUi();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profileFirebaseAuth = FirebaseAuth.getInstance();
        profileFirebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference profileDatabaseReference = profileFirebaseDatabase.getReference(profileFirebaseAuth.getUid());

        profileDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                upFn.setText(userProfile.getFName());
                upLn.setText(userProfile.getLName());
                upGen.setText(userProfile.getGender());
                upEmail.setText(userProfile.getEmail());
                upStd.setText(userProfile.getStd());
                upDob.setText(userProfile.getDOB());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateUserInfo.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        upSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String upFN = upFn.getText().toString();
                String upLN = upLn.getText().toString();
                String upGEN = upGen.getText().toString();
                String upEMAIl = upEmail.getText().toString();
                String upSTD = upStd.getText().toString();
                String upDOB = upDob.getText().toString();

                UserProfile userProfile = new UserProfile(upFN,upLN,upEMAIl,upSTD,upDOB,upGEN);

                profileDatabaseReference.setValue(userProfile);

                finish();
            }
        });
    }

    private void setUi(){
        uppropicImg = (ImageView)findViewById(R.id.imviewupdatedata);
        upFn = (EditText)findViewById(R.id.upetfn);
        upLn = (EditText)findViewById(R.id.upetln);
        upGen = (EditText)findViewById(R.id.upetgen);
        upEmail = (EditText)findViewById(R.id.upetemail);
        upStd = (EditText)findViewById(R.id.upetstd);
        upDob = (EditText)findViewById(R.id.upetdob);
        upSave = (Button)findViewById(R.id.upbtnsave);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}

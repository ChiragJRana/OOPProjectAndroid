package com.example.splash_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profilePic;
    private TextView profileFN,profileLN,profileGender,profileEmail,profileStd,profileDOB;
    private Button profileEdit,profilechangePassword;
    private FirebaseDatabase profileFirebaseDatabase;
    private FirebaseAuth profileFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setUi();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profileFirebaseAuth = FirebaseAuth.getInstance();
        profileFirebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference profileDatabaseReference = profileFirebaseDatabase.getReference(profileFirebaseAuth.getUid());

        profileDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                profileFN.setText("FName: "+userProfile.getFName());
                profileLN.setText("LName: "+userProfile.getLName());
                profileGender.setText("Gender: "+userProfile.getGender());
                profileEmail.setText("Email: "+userProfile.getEmail());
                profileStd.setText("Standard: "+userProfile.getStd());
                profileDOB.setText("DOB: "+userProfile.getDOB());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,UpdateUserInfo.class));
            }
        });

        profilechangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,ChangePassword.class));
            }
        });
    }

    private void setUi(){
        profilePic = (ImageView)findViewById(R.id.imageView);
        profileFN = (TextView)findViewById(R.id.tvFName);
        profileLN = (TextView)findViewById(R.id.tvLName);
        profileGender = (TextView)findViewById(R.id.tvGender);
        profileEmail = (TextView)findViewById(R.id.tvEmail);
        profileStd = (TextView)findViewById(R.id.tvStd);
        profileDOB = (TextView)findViewById(R.id.tvDOB);
        profileEdit = (Button)findViewById(R.id.btnEdit);
        profilechangePassword = (Button)findViewById(R.id.btnchangepwd);
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

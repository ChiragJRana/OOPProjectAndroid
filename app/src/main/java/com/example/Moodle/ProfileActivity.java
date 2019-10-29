package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Moodle.Student.User_Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profilePic;
    private TextView profileFN,profileLN,profileGender,profileEmail,profileStd,profileDOB;
    private Button profileEdit,profilechangePassword;
    private FirebaseDatabase profileFirebaseDatabase;
    private FirebaseAuth profileFirebaseAuth;
    private FirebaseStorage profileFirebaseStorage;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setUi();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        profileFirebaseAuth = FirebaseAuth.getInstance();
        profileFirebaseDatabase = FirebaseDatabase.getInstance();
        profileFirebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference profileDatabaseReference = profileFirebaseDatabase.getReference();
        StorageReference profileStorageReference = profileFirebaseStorage.getReference();

        firebaseUser = profileFirebaseAuth.getCurrentUser();

        profileStorageReference.child("Student Images").child(firebaseUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //profilePic.setImageUri(uri);              //We cannot implement this line since the data is in the form of a link so it cannot be assigned directly to the image view...
                Picasso.get().load(uri).placeholder(R.drawable.ic_launcher_foreground).into(profilePic);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this,"Failed to download image...",Toast.LENGTH_LONG).show();
            }
        });      //This can also be replaced by child(...).child(...).

        profileDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User_Profile userProfile = dataSnapshot.child("Students").child(firebaseUser.getUid()).getValue(User_Profile.class);
                profileFN.setText("First Name: "+userProfile.getFName());
                profileLN.setText("Last Name: "+userProfile.getLName());
                profileGender.setText("Gender: "+userProfile.getGender());
                profileEmail.setText("Email ID: "+userProfile.getEmail());
                profileStd.setText("Standard: "+userProfile.getStd());
                profileDOB.setText("Date Of Birth: "+userProfile.getDOB());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, Update_Student_Profile.class));
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
        profileEdit.setTranslationX(-1000f);
        profilechangePassword.setTranslationX(1000f);
        profilePic.setTranslationY(-1000f);
        profileEdit.animate().translationXBy(1000f).setDuration(2000);
        profilechangePassword.animate().translationXBy(-1000f).setDuration(2000);
        profilePic.animate().translationYBy(1000f).setDuration(2000);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}

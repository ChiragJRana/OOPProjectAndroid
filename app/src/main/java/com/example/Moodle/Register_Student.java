package com.example.Moodle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Moodle.Student.Login_Student;
import com.example.Moodle.Student.User_Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Register_Student extends AppCompatActivity {

    private String First_Name;
    private String Last_Name;
    private EditText Email;
    private EditText Password;
    private RadioGroup gender;
    private RadioButton selectGender;
    private EditText branch;
    private TextView dob;
    private Button btn_Register;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private ImageView imageViewProPic;
    private static int PICK_IMAGE=123;
    Uri imagePath;                              //Uri - Unique Resource Identifier...
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;
    private int year,month,dayofMonth;
    private Calendar calendar;
    private DatePickerDialog datepickerdialog ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData()!=null){
            imagePath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                imageViewProPic.setImageBitmap(bitmap);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__student);
        setUiId();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        dob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datepickerdialog = new DatePickerDialog(Register_Student.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dob.setText(day +"/"+ (month+1)+"/"+year );

                    }

                }, year, month,dayofMonth);
                datepickerdialog.show();
            }
        });
//
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();
        /*priorityList = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,priorityList);
        spstd.setAdapter(adapter);
        spstd.setOnItemSelectedListener(this);*/
        imageViewProPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");          //One can use application/* for all kinds of files like doc pdf etc
                //One can user audio/* to support all kinds os audio...
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
            }
        });

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    String user_Email = Email.getText().toString().trim();
                    String user_Password = Password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_Email,user_Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendEmailVerification();
                                /*Toast.makeText(Register_Student.this,"Registration successful",Toast.LENGTH_SHORT).show();*/
                            }else{
                                Toast.makeText(Register_Student.this,"Registration Unsuccessful",Toast.LENGTH_SHORT).show();
                                /*Intent intent = new Intent(Register_Student.this,Login_Student.class);
                                startActivity(intent);
                                Register_Student.this.finish();*/
                            }

                        }
                    });
                }
            }
        });

    }
    private void setUiId(){
        Email = (EditText)findViewById(R.id.et_Email);
        Password = (EditText)findViewById(R.id.et_password);
        gender = (RadioGroup)findViewById(R.id.radioGroup);
        branch = (EditText) findViewById(R.id.et_std);
        dob = (TextView)findViewById(R.id.tv_DOB);
        imageViewProPic = (ImageView)findViewById(R.id.imProfile);
        btn_Register = (Button)findViewById(R.id.btn_Register);
    }

    private boolean check(){
        boolean result=false;
        String userEmail=Email.getText().toString();
        if(userEmail.isEmpty() || !userEmail.contains("@spit.ac.in")){
            Email.setError("Invalid");
            Toast.makeText(this,"Invalid ID",Toast.LENGTH_SHORT).show();
            return result;
        }
        String[] namesur = userEmail.split("@");
        if(!namesur[0].contains(".")){
            Email.setError("Invalid");
            Toast.makeText(this,"Invalid ID",Toast.LENGTH_SHORT).show();
            return false;
        };
        First_Name = namesur[0].split("[.]")[0];
        String password = Password.getText().toString();
        Last_Name= namesur[0].split("[.]")[1];

        if(password.length()< 10){
            Password.setError("Too Short");
            Toast.makeText(this, "Password too Short", Toast.LENGTH_SHORT).show();
        }
        String branchval = branch.getText().toString();
        switch(branchval.toUpperCase()){
            case "FECOMPS":
                 break;
            case "SECOMPS":
                break;
            case "TECOMPS":
                break;
            case "BECOMPS":
                break;
            case "FEIT":
                break;
            case "SEIT":
                break;
            case "TEIT":
                break;
            case "BEIT":
                break;
            case "FEETRX":
                break;
            case "SEETRX":
                break;
            case "TEETRX":
                break;
            case "BEETRX":
                break;
            case "FEEXTC":
                break;
            case "SEEXTC":
                break;
            case "TEEXTC":
                break;
            case "BEEXTC":
                break;
            default:
                branch.setError("Invalid");
                return false;

        }

        if(dob.getText().toString().equals("Date Of Birth")){
            Toast.makeText(this,"Please enter the date of birth",Toast.LENGTH_SHORT).show();
            return result;
        }

        if(imagePath==null){
            Toast.makeText(this,"Please select the image...",Toast.LENGTH_SHORT).show();
            return result;
        }
        result=true;
        return result;
    }
    /*private void getSpinner(){
        spcntcode.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,CountryData.countryNames));

    }*/
    private String getRadioText(){
        int radioId = gender.getCheckedRadioButtonId();
        selectGender = findViewById(radioId);
        return selectGender.getText().toString();
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        uploadUserData();
                        Toast.makeText(Register_Student.this,"Successfully Registered.Verification email sent",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        //Intent intent = new Intent(Register_Student.this, Login_Student.class);
                        //intent.putExtra("Name",FN.getText().toString());    This line is logically not correct
                        //As tis line would given the name in the title bar once on the creation of the user but not
                        //when the user loggs in after logging out
                        //Since this line fetches data from the Register_student.class and not from the fire-base...
                        //startActivity(intent);
                    }else{
                        Toast.makeText(Register_Student.this,"Network problem",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void uploadUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();                 //This getUid method is to get the User ID which is a hash value from the firebase...
        //In the above line we might have given the name as the reference but there could have been multiple names creating problems in data manupilation...
        firebaseUser = firebaseAuth.getCurrentUser();
        StorageReference imageReference = storageReference.child("Student Images").child(firebaseUser.getUid());
        //Here the data is stored in this manner on firebase Uid/Images/profile_pics.jpg...
        //Here in the first child we can even specify audio... instead of Images...
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register_Student.this,"Unsuccessfull to uploaded image...",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Register_Student.this,"Successfully uploaded image...",Toast.LENGTH_SHORT).show();
            }
        });

        User_Profile userProfile = new User_Profile(First_Name,Last_Name,Email.getText().toString(),branch.getText().toString(),dob.getText().toString(),getRadioText());
        databaseReference.child("Students").child(Objects.requireNonNull(firebaseAuth.getUid())).setValue(userProfile);
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


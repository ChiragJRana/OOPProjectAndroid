package com.example.Moodle.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Moodle.ForgotPassword;
import com.example.Moodle.R;
import com.example.Moodle.Register_Student;
import com.example.Moodle.Student_Home_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


import static java.lang.Thread.sleep;

public class Login_Student extends AppCompatActivity {

    private String FN;
    private EditText Email;
    private EditText Password;
    private Button LoginBtn;
    private TextView Info;
    private TextView Register;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private int counter=5;
    private TextView forgotPassword;
    private ImageView applogo;
    private FirebaseDatabase firebaseDatabase;

    public void setingToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
    public void setTextInfo(){
        String infostring = getString(R.string.setTextInfo) + counter;
        Info.setText(infostring);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);              //Asked to remove the title bar..
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Email = (EditText) findViewById(R.id.et_email);
        Password = (EditText) findViewById(R.id.et_password);
        LoginBtn = (Button) findViewById(R.id.btn_login);
        Info = (TextView) findViewById(R.id.tv_attempts);
        Register = (TextView) findViewById(R.id.tv_register);
        forgotPassword = (TextView)findViewById(R.id.tv_forgotPass);
        applogo = findViewById(R.id.imageView2);
        applogo.animate().alpha(1f).setDuration(1000);
        setTextInfo();

        //Firebase functions...
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            finish();
            startActivity(new Intent(Login_Student.this, Student_Home_page.class));//Here start activity is very important since without start we cannot jump to the next activity...
        }

        //progress dialogue
        progressDialog = new ProgressDialog(this);

        LoginBtn.setOnClickListener(new View.OnClickListener() {                                //Method to do something when a button or a text is clicked...
            @Override
            public void onClick(View view) {
                if (check()) {            //Check the methods first get the text and then convert it to string...
                    validate(Email.getText().toString(), Password.getText().toString());
                }
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Student.this, Register_Student.class);
                startActivity(intent);
                //Login_Student.this.finish();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Student.this, ForgotPassword.class));
                //Login_Student.this.finish();
            }
        });
    }
    private boolean check() {
        String userName = Email.getText().toString();
        String userPassword = Password.getText().toString();
        if (userName.isEmpty()) {
            setingToast("Please enter the user name");
            Email.setError("Empty");
            return false;
        }
        if (!userName.contains("@spit.ac.in")){
            Email.setError("Invalid");
        }
        if (userPassword.isEmpty()|| userPassword.length() < 10) {
            Password.setError("Too short");
            setingToast("Please enter the password");
            return false;
        }
        return true;
    }

    public void validate(String userEmail, String userPassword) {
        progressDialog.setMessage("Please wait while the verification is done");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    //finish();
                    /*Toast.makeText(Login_Student.this, "Successful Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login_Student.this, Student_Home_page.class));*/
                    checkEmailVerification();
                } else {
                    setingToast("Unsuccessful");
                    progressDialog.dismiss();
                    counter--;
                    setTextInfo();
                    if (counter == 0) {
                        LoginBtn.setEnabled(false);
                    }
                }
            }
        });
    }
    public void checkEmailVerification(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Boolean check = firebaseUser.isEmailVerified();

        if(check){
            //finish();
            Intent intent = new Intent(Login_Student.this, Student_Home_page.class);
            //Here FN is my string...
            startActivity(intent);
        }else{
            setingToast("Please verify the email");
            firebaseAuth.signOut();
        }
    }
}


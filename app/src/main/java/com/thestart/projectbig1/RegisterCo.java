package com.thestart.projectbig1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterCo extends AppCompatActivity {
  TextView textViewLog;
  EditText nameView, emailView, usernameView, phoneView, passwdView;
  Button singButton;
  FirebaseDatabase firebaseDatabase;
  DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_co);
        textViewLog = findViewById(R.id.loginRedirectText);
        nameView = findViewById(R.id.nameid);
        emailView = findViewById(R.id.emailid);
        usernameView = findViewById(R.id.usernameid);
        phoneView = findViewById(R.id.phonenumberid);
        passwdView = findViewById(R.id.passwdid);
        textViewLog = findViewById(R.id.loginRedirectText);
        singButton = findViewById(R.id.singupButtonid);

        singButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    submitData();
            }
        });
     textViewLog.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(RegisterCo.this, LoginCo.class);
             startActivity(intent);

         }
     });


    }

    private void submitData() {
        String Bname = nameView.getText().toString().trim();
        String Bemail = emailView.getText().toString().trim();
        String Busername = usernameView.getText().toString().trim();
        String Bphonenumber = phoneView.getText().toString().trim();
        String Bpasswd = passwdView.getText().toString().trim();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Project_Big1data");
        myBase mybase = new myBase(Bname, Bemail, Busername, Bphonenumber, Bpasswd);
        database.child(Busername).setValue(mybase);
        Toast.makeText(this, "You Have Signed Up Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterCo.this, LoginCo.class);
        startActivity(intent);
    }

}
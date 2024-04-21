package com.thestart.projectbig1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginCo extends AppCompatActivity {
    TextView textViewSign;
    EditText loginUserview, loginPasswd;
    Button Loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_co);
        textViewSign = findViewById(R.id.singupRedirectText);
        loginUserview = findViewById(R.id.log_usernameid);
        loginPasswd = findViewById(R.id.log_passwdid);
        Loginbutton = findViewById(R.id.loginButtonid);

      Loginbutton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (!validateUsername() | !validatePasswd()){

              }else {
                  checkUser();
              }
          }
      });
     textViewSign.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(LoginCo.this, RegisterCo.class);
             startActivity(intent);
         }
     });

    }

    private void checkUser() {
        String userUserview = loginUserview.getText().toString();
        String userloginPasswd = loginPasswd.getText().toString();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Project_Big1data");
        Query checkUserQuery = database.orderByChild("username").equalTo(userUserview);

        checkUserQuery.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    loginUserview.setError(null);
                    String paswdFromdb = snapshot.child(userUserview).child("passwd").getValue(String.class);
                    if (paswdFromdb.equals(userloginPasswd)){
                        loginUserview.setError(null);

                        String nameFromdb = snapshot.child(userUserview).child("name").getValue(String.class);
                        String emailFromdb = snapshot.child(userUserview).child("email").getValue(String.class);
                        String usernameFromdb = snapshot.child(userUserview).child("username").getValue(String.class);
                        String phonenumberFromdb = snapshot.child(userUserview).child("phone").getValue(String.class);
                        Intent intent = new Intent(LoginCo.this, MainActivity.class);
                        intent.putExtra("name", nameFromdb);
                        intent.putExtra("email", emailFromdb);
                        intent.putExtra("username", usernameFromdb);
                        intent.putExtra("phone", phonenumberFromdb);
                        intent.putExtra("passwd", paswdFromdb);

                        startActivity(intent);
                    }else {
                        loginPasswd.setError("Invalid Credentials");
                        loginPasswd.requestFocus();
                    }
                }else {
                    loginUserview.setError("user Does not exists");
                    loginUserview.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean validatePasswd() {
        String val = loginPasswd.getText().toString();
        if (val.isEmpty()){
            loginPasswd.setError("Password cannot be empty");
            return false;
        }else {
            loginPasswd.setError(null);
            return true;
        }

    }

    private boolean validateUsername() {
        String val = loginUserview.getText().toString();
        if(val.isEmpty()){
            loginUserview.setError("User Name cannot be empty");
            return false;
        }else {
            loginUserview.setError(null);
            return true;
        }
    }
}
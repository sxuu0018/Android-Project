package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private ActivityRegisterBinding activityRegisterBinding;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = activityRegisterBinding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();

        activityRegisterBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String email_txt = "china13920801365@gmail.com";
//                String password_txt = "123456";

                String email_txt = activityRegisterBinding.username.getText().toString();
                String password_txt = activityRegisterBinding.password.getText().toString();

                if (isUsernameLegal(email_txt)) {
                    auth.createUserWithEmailAndPassword(email_txt,password_txt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                // do something, e.g. start the other activity
                                Toast.makeText(Register.this, "Register successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this,Login.class);
                                startActivity(intent);
                            }else {
                                //do something, e.g. give a message
                                Toast.makeText(Register.this, "Unsuccessfully!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register.this, "Username should be an email!", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
    public boolean isUsernameLegal(String username){
        boolean result = false;
        if(username != null && username.contains("@") &&  username.contains(".")){
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}
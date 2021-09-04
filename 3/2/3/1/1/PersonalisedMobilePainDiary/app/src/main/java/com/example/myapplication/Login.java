package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.WeatherUtil.WeatherUtil;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Login extends AppCompatActivity {
    private ActivityLoginBinding activityLoginBinding;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();

        activityLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String txt_email = "china13920801365@gmail.com";
//                String txt_pwd = "123456";
                String txt_email = activityLoginBinding.username.getText().toString();
                String txt_pwd = activityLoginBinding.password.getText().toString();
                if (isUsernameLegal(txt_email)){
                    auth.signInWithEmailAndPassword(txt_email, txt_pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //do something e.g. show a message or start another activity

                            SharedPreferences sharedPreferences= getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");// HH:mm:ss
                            Date date = new Date(System.currentTimeMillis());
                            String dateString = simpleDateFormat.format(date);

                            editor.putString("name", txt_email);
                            editor.putString("password", txt_pwd);
                            editor.putString("Date",dateString);
                            editor.commit();

                            Intent intent = new Intent(Login.this,Weather.class);
                            startActivity(intent);
                            Toast.makeText(Login.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                        }

                    });
                } else {
                    Toast.makeText(Login.this, "Username should be an Email!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        activityLoginBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
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
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityTimePickerBinding;


public class TimePicker extends AppCompatActivity {
    private ActivityTimePickerBinding activityTimePickerBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTimePickerBinding = ActivityTimePickerBinding.inflate(getLayoutInflater());
        View view = activityTimePickerBinding.getRoot();
        setContentView(view);

        activityTimePickerBinding.buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(TimePicker.this,PainDataEntry.class);
                int hour = activityTimePickerBinding.timePicker.getHour();
                int min = activityTimePickerBinding.timePicker.getMinute();

                String time = hour + ":" + min;
                intent.putExtra("Time",time);

//                Bundle bundle = new Bundle();
//                bundle.putInt("Hour",hour);
//                bundle.putInt("Minute",hour);
//                intent.putExtra("Time",bundle);
                startActivity(intent);
            }
        });



    }
}
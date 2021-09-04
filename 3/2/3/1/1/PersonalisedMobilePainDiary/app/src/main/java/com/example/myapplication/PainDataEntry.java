package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityPainDataEntryBinding;
import com.example.myapplication.fragment.AddFragment;
import com.example.myapplication.fragment.HomeFragment;

public class PainDataEntry extends AppCompatActivity {
    private ActivityPainDataEntryBinding activityPainDataEntryBinding;
    private String painlocationVal;
    private String painlevelVal;
    private String moodVal;
    private String stepsVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPainDataEntryBinding = ActivityPainDataEntryBinding.inflate(getLayoutInflater());
        View view = activityPainDataEntryBinding.getRoot();
        setContentView(view);

        activityPainDataEntryBinding.spinnerPainLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] painlevel = getResources().getStringArray(R.array.PainLevel);
                painlevelVal = painlevel[position];
                //Toast.makeText(PainDataEntry.this, "You select: " + painlevel[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        activityPainDataEntryBinding.spinnerPainLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] painlocation = getResources().getStringArray(R.array.PainLocation);
                painlocationVal = painlocation[position];
                //Toast.makeText(MainActivity.this, "You select: " + painlocation[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        activityPainDataEntryBinding.spinnerMood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] mood = getResources().getStringArray(R.array.mood);
                moodVal = mood[position];
                //Toast.makeText(MainActivity.this, "You select: " + mood[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Toast.makeText(MainActivity.this, "You select: " + editPhysicalsteps, Toast.LENGTH_LONG).show();

        activityPainDataEntryBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  editGoal = activityPainDataEntryBinding.EditGoal.getText().toString();
                //Toast.makeText(MainActivity.this, "You select: " + editGoal, Toast.LENGTH_LONG).show();

                String editPhysicalsteps = activityPainDataEntryBinding.Physicalsteps.getText().toString();
                stepsVal = editPhysicalsteps;
                //activityMainBinding.spinnerPainLevel.setOnItemClickListener(null);
                if (editGoal.equals("") || editPhysicalsteps.equals("")){
                    Toast.makeText(PainDataEntry.this, "Incomplete!", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(PainDataEntry.this,ViewPage.class);
                    intent.putExtra("painlevelVal",painlevelVal);
                    intent.putExtra("painlocationVal",painlocationVal);
                    intent.putExtra("stepsVal",stepsVal);
                    intent.putExtra("stepsGoal",editGoal);
                    intent.putExtra("mood",moodVal);

                    //Toast.makeText(PainDataEntry.this,""+editPhysicalsteps.length(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_LONG).show();

                    activityPainDataEntryBinding.EditGoal.setEnabled(false);
                    activityPainDataEntryBinding.Physicalsteps.setEnabled(false);


                    activityPainDataEntryBinding.spinnerPainLevel.setFocusable(false);
                    activityPainDataEntryBinding.spinnerPainLevel.setClickable(false);
                    startActivity(intent);

                }
            }
        });

        activityPainDataEntryBinding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityPainDataEntryBinding.EditGoal.setEnabled(true);
                activityPainDataEntryBinding.Physicalsteps.setEnabled(true);
                Toast.makeText(PainDataEntry.this, "Start edit!", Toast.LENGTH_LONG).show();

                // String test = "";
                //Toast.makeText(MainActivity.this, "length is:+test.length()", Toast.LENGTH_LONG).show();
            }
        });

        activityPainDataEntryBinding.buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainDataEntry.this,TimePicker.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String time = intent.getStringExtra("Time");
        activityPainDataEntryBinding.time.setText(time);

        activityPainDataEntryBinding.backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainDataEntry.this,Weather.class);
                startActivity(intent);
            }
        });

    }
}
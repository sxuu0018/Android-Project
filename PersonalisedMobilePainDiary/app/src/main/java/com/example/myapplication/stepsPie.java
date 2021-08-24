package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityStepsPieBinding;
import com.example.myapplication.fragment.AddFragment;
import com.example.myapplication.fragment.HomeFragment;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class stepsPie extends AppCompatActivity {

    private ActivityStepsPieBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStepsPieBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(stepsPie.this, PainDataEntry.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String goalString = intent.getStringExtra("goal");
        String steps = intent.getStringExtra("steps");


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        String dateString = simpleDateFormat.format(date);

        binding.date.setText(dateString);

        int goalInt = 0;

        goalInt =  Integer.parseInt(goalString);
        int stepsInt = 0;
        stepsInt = Integer.parseInt(steps);
        int lastSteps = goalInt - stepsInt;
        if (lastSteps < 0){
            lastSteps = 0;
        }
        binding.stillHave.setText("You still have: "+lastSteps+" steps!\nFinished: "+steps);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(lastSteps, "You still have"));
        entries.add(new PieEntry(stepsInt, "Finished"));

//        entries.add(new PieEntry(20f, "back"));
//        entries.add(new PieEntry(20f, "neck"));
//        entries.add(new PieEntry(20f,  "head"));
//        entries.add(new PieEntry(20f,  "knees"));
//        entries.add(new PieEntry(20f,  "hips"));
//        entries.add(new PieEntry(20f,  "abdomen"));
//        entries.add(new PieEntry(20f,  "elbows"));
//        entries.add(new PieEntry(20f,  "shoulders"));
//        entries.add(new PieEntry(20f,  "shins"));
//        entries.add(new PieEntry(20f,  "jaw"));
//        entries.add(new PieEntry(20f,  "facial"));

        PieDataSet set = new PieDataSet(entries, "Results");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(set);
        binding.pieChart.animateY(4000);
        binding.pieChart.setData(data);
        binding.pieChart.invalidate();

    }
}
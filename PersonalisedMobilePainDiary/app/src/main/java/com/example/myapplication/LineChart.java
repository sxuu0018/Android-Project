package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityLineChartBinding;
import com.example.myapplication.fragment.AddFragment;
import com.example.myapplication.fragment.DatePickerFragment;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class LineChart extends AppCompatActivity {
    private ActivityLineChartBinding activityLineChartBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLineChartBinding = ActivityLineChartBinding.inflate(getLayoutInflater());
        View view = activityLineChartBinding.getRoot();
        setContentView(view);

        com.github.mikephil.charting.charts.LineChart lineChart;
        LineData lineData;
        List<Entry> entryList = new ArrayList<>();
//
//        entryList.add(new Entry(50,20));
//        entryList.add(new Entry(5,10));
//        entryList.add(new Entry(7,31));
//        entryList.add(new Entry(3,14));

        entryList.add(new Entry(1,3));
        entryList.add(new Entry(2,5));
        entryList.add(new Entry(3,2));
        entryList.add(new Entry(5,1));
        entryList.add(new Entry(6,2));
        entryList.add(new Entry(7,4));
        entryList.add(new Entry(8,1));
        entryList.add(new Entry(9,2));
        entryList.add(new Entry(10,5));


        LineDataSet lineDataSet = new LineDataSet(entryList,"pain");
        //lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setFillAlpha(110);
        lineDataSet.setLabel("Date");
        lineData = new LineData(lineDataSet);

        activityLineChartBinding.lineChart.setData(lineData);

        activityLineChartBinding.lineChart.setVisibleXRangeMaximum(50);
        activityLineChartBinding.lineChart.invalidate();

        activityLineChartBinding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });



    }
}
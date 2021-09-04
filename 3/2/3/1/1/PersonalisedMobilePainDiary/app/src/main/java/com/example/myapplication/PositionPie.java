package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityPositionPieBinding;
import com.example.myapplication.entity.Customer;
import com.example.myapplication.viewmodel.CustomerViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;

public class PositionPie extends AppCompatActivity {
    private ActivityPositionPieBinding binding;
    final String CHART_URL = "";
    private CustomerViewModel customerViewModel;
    private int back = 5;
    private int neck = 5;
    private int head = 5;
    private int knees = 5;
    private int hips = 5;
    private int abdomen = 5;
    private int elbows = 5;
    private int shoulders = 5;
    private int shins = 5;
    private int jaw = 5;
    private int facial = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPositionPieBinding binding = ActivityPositionPieBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //start

//        customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CustomerViewModel.class);
//        customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
//            @Override
//            public void onChanged(@Nullable final List<Customer> customers) {
//                String allCustomers = "";
//                back = 0;
//                neck = 0;
//                head = 0;
//                knees = 0;
//                hips = 0;
//                abdomen = 0;
//                elbows = 0;
//                shoulders = 0;
//                shins = 0;
//                jaw = 0;
//                facial = 0;
//                for (Customer temp : customers) {
//                    if (temp.painPosition.equals("back")) back++;
//                    if (temp.painPosition.equals("neck")) neck++;
//                    if (temp.painPosition.equals("head"))head++;
//                    if (temp.painPosition.equals("knees"))knees++;
//                    if (temp.painPosition.equals("hips"))hips++;
//                    if (temp.painPosition.equals("abdomen"))abdomen++;
//                    if (temp.painPosition.equals("elbows"))elbows++;
//                    if (temp.painPosition.equals("shoulders"))shoulders++;
//                    if (temp.painPosition.equals("shins"))shins++;
//                    if (temp.painPosition.equals("jaw"))jaw++;
//                    if (temp.painPosition.equals("facial"))facial++;
//
//                    String customerDetails = ("ID: "+temp.uid + " PainLevel: " + temp.painLevel + " PainPosition: " + temp.painPosition + " Steps: " + temp.steps+" Mood: "+temp.mood+" Email: "+temp.email+" Date: "+temp.date+" Temperature: "+temp.temperature+" Humidity: "+temp.humidity+" Pressure: "+temp.pressure);
//                    allCustomers = allCustomers + System.getProperty("line.separator") + customerDetails;
//                }
//            }
//        });
        //end




//        binding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(PositionPie.this,SecondChart.class);
////                startActivity(intent);
//            }
//        });
//        binding.button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(MainActivity.this,ThirdChart.class);
////                startActivity(intent);
//            }
//        });
//
//        binding.buttonTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(MainActivity.this,DatePick.class);
////                startActivity(intent);
//            }
//        });


//        pie chart
//        List<PieEntry> entries = new ArrayList<>();
//        entries.add(new PieEntry(20f, "current date"));
//        entries.add(new PieEntry(80f, "remaining steps"));
//
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
//
//        PieDataSet set = new PieDataSet(entries, "Results");
//        set.setColors(ColorTemplate.COLORFUL_COLORS);
//        PieData data = new PieData(set);
//        binding.pieChart.animateY(4000);
//        binding.pieChart.setData(data);
//       binding.pieChart.invalidate();
//
// pie chart

        //database

//        int back = 0;
//        int neck = 0;
//        int head = 0;
//        int knees = 0;
//        int hips = 0;
//        int abdomen = 0;
//        int elbows = 0;
//        int shoulders = 0;
//        int shins = 0;
//        int jaw = 0;
//        int facial = 0;
//        customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CustomerViewModel.class);
//        customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
//            @Override
//            public void onChanged(@Nullable final List<Customer> customers) {
//                String allCustomers = "";
//                for (Customer temp : customers) {
//                    if (temp.painPosition.equals("back")) back++;
//                    if (temp.painPosition.equals("neck")) neck++;
//                    if (temp.painPosition.equals("head"))head++;
//                    if (temp.painPosition.equals("knees"))knees++;
//                    if (temp.painPosition.equals("hips"))hips++;
//                    if (temp.painPosition.equals("abdomen"))abdomen++;
//                    if (temp.painPosition.equals("elbows"))elbows++;
//                    if (temp.painPosition.equals("shoulders"))shoulders++;
//                    if (temp.painPosition.equals("shins"))shins++;
//                    if (temp.painPosition.equals("jaw"))jaw++;
//                    if (temp.painPosition.equals("facial"))facial++;
//
//                    String customerDetails = ("ID: "+temp.uid + " PainLevel: " + temp.painLevel + " PainPosition: " + temp.painPosition + " Steps: " + temp.steps+" Mood: "+temp.mood+" Email: "+temp.email+" Date: "+temp.date+" Temperature: "+temp.temperature+" Humidity: "+temp.humidity+" Pressure: "+temp.pressure);
//                    allCustomers = allCustomers + System.getProperty("line.separator") + customerDetails;
//                }
//            }
//        });

        //start
        Intent intent = getIntent();
        int backInt = intent.getIntExtra("back",0);

        int back = intent.getIntExtra("back",0);
        int neck = intent.getIntExtra("neck",0);
        int head = intent.getIntExtra("head",0);
        int knees = intent.getIntExtra("knees",0);
        int hips = intent.getIntExtra("hips",0);
        int abdomen = intent.getIntExtra("abdomen",0);
        int elbows = intent.getIntExtra("elbows",0);
        int shoulders = intent.getIntExtra("shoulders",0);
        int shins = intent.getIntExtra("shins",0);
        int jaw = intent.getIntExtra("jaw",0);
        int facial = intent.getIntExtra("facial",0);

        Toast.makeText(PositionPie.this,""+backInt,Toast.LENGTH_SHORT).show();
        String result = "Back: "+back+"\nneck: "+neck+"\nhead: "+head+"\nknees: "+knees+"\nhips: "+hips+"\nabdomen: "+abdomen+"\nelbows: "+elbows+"\nshoulders: "+shoulders+"\nshins: "+shins+"\njaw: "+jaw+"\nfacial: "+facial;
        binding.result.setText(result);
        //end
        //database


        //实心饼图
        List<SliceValue> pieData = new ArrayList<>();

        PieChartData pieChartData = new PieChartData(pieData);
        pieData.add(new SliceValue(back, Color.BLUE).setLabel("back"));
        pieData.add(new SliceValue(neck, Color.GREEN).setLabel("neck"));
        pieData.add(new SliceValue(head, Color.RED).setLabel("head"));
        pieData.add(new SliceValue(knees, Color.MAGENTA).setLabel("knees"));

        pieData.add(new SliceValue(hips, Color.CYAN).setLabel("hips"));
        pieData.add(new SliceValue(abdomen, Color.GRAY).setLabel("abdomen"));
        pieData.add(new SliceValue(elbows, Color.LTGRAY).setLabel("elbows"));
        pieData.add(new SliceValue(shoulders, Color.YELLOW).setLabel("shoulders"));
        pieData.add(new SliceValue(shins, Color.WHITE).setLabel("shins"));
        pieData.add(new SliceValue(jaw, Color.DKGRAY).setLabel("jaw"));
        pieData.add(new SliceValue(facial, Color.BLACK).setLabel("facial"));
        pieChartData.setHasLabels(true);
        binding.pieChart.setPieChartData(pieChartData);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        String dateString = simpleDateFormat.format(date);

        binding.Topic.setText("Data until: "+dateString);
        //实心饼图
        //bar chart
//        List<BarEntry> barEntries = new ArrayList<>();
//        barEntries.add(new BarEntry(0, 3112));
//        barEntries.add(new BarEntry(1, 4444));
//        barEntries.add(new BarEntry(2, 2222));
//        barEntries.add(new BarEntry(3, 5555));
//        barEntries.add(new BarEntry(4, 1111));
//        barEntries.add(new BarEntry(5, 3656));
//        barEntries.add(new BarEntry(6, 3435));

//        BarDataSet barDataSet = new BarDataSet(barEntries, "Steps");
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//        List<String> xAxisValues = new ArrayList<>(Arrays.asList("Sun", "Mon", "Tues", "Wed", "Thurs", "Fri","Sat"));
//        binding.barChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
//        BarData barData = new BarData(barDataSet);
//        binding.barChart.setData(barData);
//        barData.setBarWidth(1.0f);
//        binding.barChart.setVisibility(View.VISIBLE);
//        binding.barChart.animateY(4000);
//        //description will be displayed as "Description Label" if not provided
//        Description description = new Description();
//        description.setText("Daily Steps");
//        binding.barChart.setDescription(description);
//        //refresh the chart
//        binding.barChart.invalidate();
//        bar chart
    }

}
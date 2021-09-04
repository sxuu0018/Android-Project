package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.ActivityViewPageBinding;
import com.example.myapplication.entity.Customer;
import com.example.myapplication.viewmodel.CustomerViewModel;
import com.github.mikephil.charting.charts.PieChart;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ViewPage extends AppCompatActivity {
    private ActivityViewPageBinding binding;
    private CustomerViewModel customerViewModel;

    private int back = 0;
    private int neck = 0;
    private int head = 0;
    private int knees = 0;
    private int hips = 0;
    private int abdomen = 0;
    private int elbows = 0;
    private int shoulders = 0;
    private int shins = 0;
    private int jaw = 0;
    private int facial = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();

        String painlevelVal = intent.getStringExtra("painlevelVal");
        String painlocationVal = intent.getStringExtra("painlocationVal");
        String stepsVal = intent.getStringExtra("stepsVal");
        String mood = intent.getStringExtra("mood");
        String stepsGoal = intent.getStringExtra("stepsGoal");
        //double stepsVal = Double.valueOf(steps.toString());

        Toast.makeText(ViewPage.this,"Data will not be added until you confirm it!",Toast.LENGTH_LONG).show();
        Toast.makeText(ViewPage.this,"Please confirm data!",Toast.LENGTH_LONG).show();

//        Toast.makeText(ViewPage.this,painlocationVal,Toast.LENGTH_LONG).show();
//        Toast.makeText(ViewPage.this,stepsVal,Toast.LENGTH_LONG).show();

        binding.idTextField.setPlaceholderText("[Pain level: "+painlevelVal+"] [Pain location: "+painlocationVal+"] [Goal steps: "+stepsGoal+"] [Steps: "+stepsVal+"] [Mood: "+mood+"]");
        //we make sure that AndroidViewModelFactory creates the view model so it can accept the Application as the parameter
        customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CustomerViewModel.class);
        customerViewModel.getAllCustomers().observe(this, new
                Observer<List<Customer>>() {
                    @Override
                    public void onChanged(@Nullable final List<Customer> customers) {
                        String allCustomers = "";
                        SharedPreferences sharedPreferences= getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
                        String userName=sharedPreferences.getString("name","");
                        for (Customer temp : customers) {

                            if (temp.email.equals(userName)){
                                String customerDetails = ("[ID: "+temp.uid + "] [PainLevel: " + temp.painLevel + "] [PainPosition: " + temp.painPosition + "] [Goal steps: "+temp.goalSteps+"] [Steps: " + temp.steps+"] [Mood: "+temp.mood+"] [Email: "+temp.email+"] [Date: "+temp.date+"] [Temperature: "+temp.temperature+"] [Humidity: "+temp.humidity+"] [Pressure: "+temp.pressure+"]");
                                allCustomers = allCustomers + System.getProperty("line.separator") + customerDetails;
                            }


                        }
                        binding.textViewRead.setText("All data: " + allCustomers);
                    }
                });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            String name = "";
            public void onClick(View v) {

                String painlevel= painlevelVal;
                String painPosition = painlocationVal;
                //Double strSalary = stepsVal;
                String stepsGoalString = stepsGoal;
                String stepsString = stepsVal;
                String moodString = mood;

                if ((!painlevelVal.isEmpty() && painlevelVal!= null)  && (!painPosition.isEmpty() &&
                        stepsString!=null) && (!stepsString.isEmpty() && painPosition!=null)) {
                    int steps = Integer.parseInt(stepsString);
                    int stepsGoalInt = Integer.parseInt(stepsGoalString);

                    SharedPreferences sharedPreferences= getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
                    String userName=sharedPreferences.getString("name","");


                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");// HH:mm:ss
                    Date dateCurrent = new Date(System.currentTimeMillis());
                    String dateString = simpleDateFormat.format(dateCurrent);


                    String email =userName;
                    String date = dateString;
                    String temperature = sharedPreferences.getString("Temperature","");
                    String humidity = sharedPreferences.getString("Humidity","");
                    String pressure = sharedPreferences.getString("Pressure","");
                    Customer customer = new Customer(painlevel, painPosition, stepsGoalInt,steps,moodString,email,date,temperature,humidity,pressure);
                    customerViewModel.insert(customer);
                    binding.textViewAdd.setText("[PainLevel: " + painlevel + "] [painPosition: " +painPosition +"] [Goal steps: "+stepsString +"] [Steps: " + steps+"] [MoodString: "+moodString+"] [Email: "+email+"] [Temperature: "+temperature+"] [Humidity: "+humidity+"] [Pressure "+pressure+"]\n");
                }
            }});

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPage.this,PainDataEntry.class);
                startActivity(intent);
            }
        });

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customerViewModel.deleteAll();
                binding.textViewDelete.setText("All data was deleted");
            }
        });
        binding.goToPieChart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //binding.nameTextField.getEditText().setText("");

//                binding.surnameTextField.getEditText().setText("");
//                binding.salaryTextField.getEditText().setText("");

                Intent intent = new Intent(ViewPage.this, stepsPie.class);
                intent.putExtra("goal",stepsGoal);
                intent.putExtra("steps",stepsVal);
                startActivity(intent);
            }
        });
        customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CustomerViewModel.class);
        customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(@Nullable final List<Customer> customers) {
                String allCustomers = "";
                back = 0;
                neck = 0;
                head = 0;
                knees = 0;
                hips = 0;
                abdomen = 0;
                elbows = 0;
                shoulders = 0;
                shins = 0;
                jaw = 0;
                facial = 0;
                for (Customer temp : customers) {
                    if (temp.painPosition.equals("back")) back++;
                    if (temp.painPosition.equals("neck")) neck++;
                    if (temp.painPosition.equals("head"))head++;
                    if (temp.painPosition.equals("knees"))knees++;
                    if (temp.painPosition.equals("hips"))hips++;
                    if (temp.painPosition.equals("abdomen"))abdomen++;
                    if (temp.painPosition.equals("elbows"))elbows++;
                    if (temp.painPosition.equals("shoulders"))shoulders++;
                    if (temp.painPosition.equals("shins"))shins++;
                    if (temp.painPosition.equals("jaw"))jaw++;
                    if (temp.painPosition.equals("facial"))facial++;

                    String customerDetails = ("ID: "+temp.uid + " PainLevel: " + temp.painLevel + " PainPosition: " + temp.painPosition + " Steps: " + temp.steps+" Mood: "+temp.mood+" Email: "+temp.email+" Date: "+temp.date+" Temperature: "+temp.temperature+" Humidity: "+temp.humidity+" Pressure: "+temp.pressure);
                    allCustomers = allCustomers + System.getProperty("line.separator") + customerDetails;
                }
            }
        });
        binding.goToPositionChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewPage.this,PositionPie.class);
                intent.putExtra("back",back);
                intent.putExtra("neck",neck);
                intent.putExtra("head",head);
                intent.putExtra("knees",knees);
                intent.putExtra("hips",hips);
                intent.putExtra("abdomen",abdomen);
                intent.putExtra("elbows",elbows);
                intent.putExtra("shoulders",shoulders);
                intent.putExtra("shins",shins);
                intent.putExtra("jaw",jaw);
                intent.putExtra("facial",facial);

                startActivity(intent);
            }
        });
        binding.backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPage.this,Weather.class);
                startActivity(intent);
            }
        });
    }
}
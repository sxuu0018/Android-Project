package com.example.myapplication.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.PainDataEntry;
import com.example.myapplication.Weather;
import com.example.myapplication.WeatherUtil.WeatherUtil;
import com.example.myapplication.databinding.HomeFragmentBinding;
import com.example.myapplication.viewmodel.SharedViewModel;

public class HomeFragment extends Fragment {
    private SharedViewModel model;
    private HomeFragmentBinding addBinding;



    public HomeFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getBundle("weather").getString("userName");
        String Weather = bundle.getBundle("weather").getString("Weather");
        String CityName = bundle.getBundle("weather").getString("CityName");
        String Temperature = bundle.getBundle("weather").getString("Temperature");
        String Humidity = bundle.getBundle("weather").getString("Humidity");
        String Pressure = bundle.getBundle("weather").getString("Pressure");

        addBinding.userInfo.setText("Hello: "+userName);
        addBinding.location.setText("Your location: "+CityName);
        addBinding.temperature.setText("Temperature: "+Temperature);
        addBinding.humidity.setText("Humidity: "+Humidity);
        addBinding.pressure.setText("Pressure: "+Pressure);

        addBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Weather.class);
                startActivity(intent);
            }
        });

//
//        WeatherUtil weatherUtil = new WeatherUtil();
//        String city = weatherUtil.getLocalWeather();
//        addBinding.textMessage.setText(city);

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
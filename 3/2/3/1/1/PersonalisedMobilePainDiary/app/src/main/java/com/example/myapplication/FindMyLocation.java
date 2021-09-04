package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityFindMyLocationBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FindMyLocation extends AppCompatActivity {

    //    private TextView responseText;
//    private EditText weather;
//    private EditText city;
//    private EditText temperature;
    private String Weather;
    private String CityName;
    private String Tempeature;
    private String Humidity;
    private String Pressure;

    private String latitude;
    private String longitude;
    private String url;

    private ActivityFindMyLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityFindMyLocationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp();
                String newLatitude = binding.City.getText().toString();
                //Toast.makeText(FindMyLocation.this,"1"+newLatitude,Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://ip-geo-location.p.rapidapi.com/ip/check?format=json")
                            .get()
                            .addHeader("x-rapidapi-key", "263fe3a8c5mshfddf8ee9ae01b76p15e6fajsne2ab50b2d66c")
                            .addHeader("x-rapidapi-host", "ip-geo-location.p.rapidapi.com")
                            .build();
                    Response response = client.newCall(request).execute();

                    String responseData = response.body().string();
                    showResPonse(responseData);
                    parseJSONWithJSONObject(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);

            JSONObject location = jsonObject.getJSONObject("location");
            this.latitude = location.getString("latitude");
            this.longitude = location.getString("longitude");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showResPonse(final String response) {
        runOnUiThread(new Runnable() {//切换到主线程,ui界面的更改不能出现在子线程,否则app会崩溃
            @Override
            public void run() {


                binding.response.setText(response);
                binding.City.setText(latitude);
                binding.Weather.setText(longitude);
//                activityMain2Binding.Temperature.setText(Tempeature);
//                activityMain2Binding.Humidity.setText(Humidity);
//                activityMain2Binding.Pressure.setText(Pressure);
            }
        });
    }


}
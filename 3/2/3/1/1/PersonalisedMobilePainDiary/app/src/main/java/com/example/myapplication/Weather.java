package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityWeatherBinding;
import com.example.myapplication.databinding.HomeFragmentBinding;
import com.example.myapplication.fragment.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public class Weather extends AppCompatActivity {

    //    private TextView responseText;
//    private EditText weather;
//    private EditText city;
//    private EditText temperature;
    private String Weather;
    private String CityName;
    private String Temperature;
    private String Humidity;
    private String Pressure;

    private HomeFragmentBinding homeFragmentBinding;
    private ActivityWeatherBinding activityWeatherBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityWeatherBinding = ActivityWeatherBinding.inflate(getLayoutInflater());
        View view = activityWeatherBinding.getRoot();
        setContentView(view);
        sendRequestWithOkHttp();
        activityWeatherBinding.sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences= getSharedPreferences("userInfo",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Weather",Weather);
                editor.putString("CityName",CityName);
                editor.putString("Temperature",Temperature);
                editor.putString("Humidity",Humidity);
                editor.putString("Pressure",Pressure);
                editor.commit();


                String userName=sharedPreferences.getString("name","");

                Intent intent = new Intent(Weather.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName",userName);
                bundle.putString("Weather",Weather);
                bundle.putString("CityName",CityName);
                bundle.putString("Temperature",Temperature);
                bundle.putString("Humidity",Humidity);
                bundle.putString("Pressure",Pressure);
                intent.putExtra("weather",bundle);
                startActivity(intent);
            }
        });
        activityWeatherBinding.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                editor.remove("name");
                editor.remove("password");
                editor.commit();
                Intent intent = new Intent(Weather.this,Login.class);
                startActivity(intent);
                Toast.makeText(Weather.this,"Sign out successfully!",Toast.LENGTH_SHORT).show();
            }
        });


//        setContentView(R.layout.activity_main);
//        Button sendRequest = (Button) findViewById(R.id.send_request);
//        responseText = (TextView) findViewById(R.id.response);
//        weather = (EditText) findViewById(R.id.Weather);
//        city = (EditText) findViewById(R.id.City);
//        temperature = (EditText) findViewById(R.id.Temperature);
//        sendRequest.setOnClickListener(this);

//        activityWeatherBinding.sendRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendRequestWithOkHttp();
//            }
//        });
    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.send_request) {
//            sendRequestWithOkHttp();
//        }
//    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建一个OkHttp实例
                    //中文城市温度
                    // Request request = new Request.Builder().url("https://api.seniverse.com/v3/weather/now.json?key=S5mCGJVgaTRNUcGYZ&location=melbourne&language=zh-Hans&unit=c").build();//创建Request对象发起请求,记得替换成你自己的key
                    // 根据城市找温度
                    //Request request = new Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?q=London&appid=4968db3c1e55a00fe71d2becb3eee15b").build();//创建Request对象发起请求,记得替换成你自己的key
                    //根据经纬度获得天气
                    Request request = new Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?lat=-37.8136&lon=144.9631&appid=4968db3c1e55a00fe71d2becb3eee15b").build();//创建Request对象发起请求,记得替换成你自己的key



//
//                    Request request = new Request.Builder()
//                            .url("https://weatherapi-com.p.rapidapi.com/timezone.json?q=%3CREQUIRED%3E")
//                            .get()
//                            .addHeader("x-rapidapi-key", "263fe3a8c5mshfddf8ee9ae01b76p15e6fajsne2ab50b2d66c")
//                            .addHeader("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
//                            .build();


//
//                    Request request = new Request.Builder()
//                            .url("https://weatherbit-v1-mashape.p.rapidapi.com/alerts?lat=38.5&lon=-78.5")
//                            .get()
//                            .addHeader("x-rapidapi-key", "263fe3a8c5mshfddf8ee9ae01b76p15e6fajsne2ab50b2d66c")
//                            .addHeader("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
//                            .build();


                    Response response = client.newCall(request).execute();//创建call对象并调用execute获取返回的数据
                    String responseData = response.body().string();
                    showResponse(responseData);//显示原始数据和解析后的数据
                    parseJSONWithJSONObject(responseData);//解析SSON数据
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData) {//用JSONObect解析JSON数据
        try {
            JSONObject jsonObject = new JSONObject(jsonData);

//            JSONArray results = jsonObject.getJSONArray("results");   //得到键为results的JSONArray
//            JSONObject now = results.getJSONObject(0).getJSONObject("now");//得到键值为"now"的JSONObject
//            JSONObject location = results.getJSONObject(0).getJSONObject("location");   //得到键值为location的JSONObject
//
//            Weather = now.getString("text");//得到"now"键值的JSONObject下的"text"属性,即天气信息
//            CityName = location.getString("path");  //获得城市名
//            Temperature = now.getString("temperature"); //获取温度

            JSONArray weather1 = jsonObject.getJSONArray("weather");
            JSONObject weather = weather1.getJSONObject(0);
            Weather = weather.getString("main");

            JSONObject main =  jsonObject.getJSONObject("main");
            String stringTemperature = main.getString("temp");
            Pressure = main.getString("pressure");
            Humidity = main.getString("humidity");

            int intTemperature = (int) (Float.parseFloat(stringTemperature) - 273.15);
            Temperature = String.valueOf(intTemperature);
            CityName = jsonObject.getString("name");



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {//切换到主线程,ui界面的更改不能出现在子线程,否则app会崩溃
            @Override
            public void run() {
                //activityWeatherBinding.response.setText(response);

                SharedPreferences sharedPreferences= getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
                String userName=sharedPreferences.getString("name","");

                activityWeatherBinding.Title.setText("Hello "+userName);
                activityWeatherBinding.City.setText("My location: "+CityName);
                activityWeatherBinding.Weather.setText("Weather: "+Weather);
                activityWeatherBinding.Temperature.setText("Temperature: "+Temperature);
                activityWeatherBinding.Humidity.setText("Humidity: "+Humidity);
                activityWeatherBinding.Pressure.setText("Pressure: "+Pressure);
                //homeFragmentBinding.textMessage.setText(Pressure);
            }
        });
    }

}

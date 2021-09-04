package com.example.myapplication.WeatherUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Weather;
//import com.example.myapplication.databinding.ActivityWeatherBinding;
//import com.example.myapplication.databinding.HomeFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherUtil {
    private String Weather;
    private String CityName;
    private String Temperature;
    private String Humidity;
    private String Pressure;
    private String responseData;

    public String getResult(){
        sendRequestWithOkHttp();
        return this.getCityName();
    }

    private void sendRequestWithOkHttp() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建一个OkHttp实例
                    Request request = new Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?lat=-37.8136&lon=144.9631&appid=4968db3c1e55a00fe71d2becb3eee15b").build();//创建Request对象发起请求,记得替换成你自己的key
                    Response response = client.newCall(request).execute();//创建call对象并调用execute获取返回的数据
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);//解析SSON数据
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    private void parseJSONWithJSONObject(String jsonData) {//用JSONObect解析JSON数据
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
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






    public String getWeather() {
        return Weather;
    }

    public void setWeather(String weather) {
        Weather = weather;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getPressure() {
        return Pressure;
    }

    public void setPressure(String pressure) {
        Pressure = pressure;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }


}
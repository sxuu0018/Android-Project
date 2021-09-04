package com.example.myapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "pain_level")
    @NonNull
    public String painLevel;
    @ColumnInfo(name = "pain_position")
    @NonNull
    public String painPosition;

    public int goalSteps;
    public int steps;

    @ColumnInfo(name = "mood")
    public String mood;

    @ColumnInfo(name = "email")
    @NonNull
    public String email;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "temperature")
    public String temperature;

    @ColumnInfo(name = "humidity")
    public String humidity;

    @ColumnInfo(name = "pressure")
    public String pressure;


    public Customer(@NonNull String painLevel, @NonNull String painPosition, int goalSteps,int steps, String mood, @NonNull String email, String date, String temperature, String humidity, String pressure) {
        this.painLevel=painLevel;
        this.painPosition=painPosition;
        this.goalSteps=goalSteps;
        this.steps = steps;
        this.mood = mood;
        this.email = email;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }
}
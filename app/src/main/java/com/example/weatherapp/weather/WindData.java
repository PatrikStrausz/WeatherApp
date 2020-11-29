package com.example.weatherapp.weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WindData implements Serializable {
  @SerializedName("speed")
  private double speed;

  @SerializedName("deg")
  private double deg;

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public double getDeg() {
    return deg;
  }

  public void setDeg(double deg) {
    this.deg = deg;
  }
}
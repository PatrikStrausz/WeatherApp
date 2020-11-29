package com.example.weatherapp.weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SysData implements Serializable {

    @SerializedName("pod")
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}
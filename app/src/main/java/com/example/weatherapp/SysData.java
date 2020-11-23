package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class SysData {

    @SerializedName("pod")
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}

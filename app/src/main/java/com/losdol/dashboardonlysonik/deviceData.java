package com.losdol.dashboardonlysonik;

import com.google.firebase.Timestamp;

import java.util.HashMap;

public class deviceData {
    public Boolean online;
    public HashMap<String, Long> sensors;
    public String username;
    public Timestamp timestamp;

    public deviceData() {}

    public deviceData(Boolean online, HashMap<String, Long> sensors, String username, Timestamp timestamp){
        this.online = online;
        this.sensors = sensors;
        this.timestamp = timestamp;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public HashMap<String, Long> getSensors() {
        return sensors;
    }

    public Boolean getOnline() {
        return online;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

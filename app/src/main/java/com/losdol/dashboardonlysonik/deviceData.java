package com.losdol.dashboardonlysonik;

import com.google.firebase.Timestamp;

import java.util.HashMap;

public class deviceData {
    public Boolean online;
    public Float tds_read, pH, level_Air, suhu_Air, ndvi, pgi, suhu_Udara, kelembaban, leaf_area, dummy1;
    public String username;
    public Timestamp timestamp;


    public deviceData() {}

    public deviceData(Boolean online, Float tds_read, Float pH,
                      Float suhuAir, Float suhuUdara, Float kelembaban,
                      Float levelAir, Float ndvi, Float pgi, Float dummy1, Float leaf_area,
                      String username, Timestamp timestamp){
        this.online = online;
        this.tds_read = tds_read; this.pH = pH; this.suhu_Air = suhuAir;
        this.suhu_Udara = suhuUdara; this.kelembaban = kelembaban;
        this.level_Air = levelAir; this.ndvi = ndvi; this.pgi = pgi;
        this.dummy1 = dummy1;
        this.timestamp = timestamp;
        this.username = username;
        this.leaf_area = leaf_area;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getOnline() {
        return online;
    }

    public Float getTDS() {
        return tds_read;
    }

    public Float getpH() {
        return pH;
    }

    public Float getLevel_Air() {
        return level_Air;
    }

    public Float getSuhu_Air() {
        return suhu_Air;
    }

    public Float getNdvi() {
        return ndvi;
    }

    public Float getPgi() {
        return pgi;
    }

    public Float getLeaf_area() {
        return leaf_area;
    }

    public Float getSuhu_Udara() {
        return suhu_Udara;
    }

    public Float getKelembaban() {
        return kelembaban;
    }

    public Float getDummy1() {
        return dummy1;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

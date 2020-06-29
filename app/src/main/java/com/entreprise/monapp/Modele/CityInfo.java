package com.entreprise.monapp.Modele;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class CityInfo {
    private String name;
    private String country;
    private String latitude;
    private String longitude;
    private String elevation;
    private String sunrise;
    private String sunset;
    private String sInitialJson;


    public CityInfo(String sJson) throws JSONException {
        sInitialJson = sJson;

        JSONObject jObject = new JSONObject(sJson);
        name = jObject.getString("name");
        country = jObject.getString("country");
        sunrise = jObject.getString("sunrise");
        sunset = jObject.getString("sunset");
    }

    public String getsInitialJson(){
        return sInitialJson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}

package com.entreprise.monapp.Modele;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentCondition {
    private String date;
    private Date mDate;
    private String hour;
    private Integer tmp;
    private Integer wndSpd;
    private Integer wndGust;
    private String wndDir;
    private Double pressure;
    private Integer humidity;
    private String condition;
    private String conditionKey;
    private String icon;
    private String iconBig;
    private String sInitialJson;

    public CurrentCondition(String sJson) throws JSONException {
        sInitialJson = sJson;

        JSONObject jObject = new JSONObject(sJson);
        date = jObject.getString("date");
        hour = jObject.getString("hour");
        condition = jObject.getString("condition");
        icon = jObject.getString("icon");

        try {
            mDate = new SimpleDateFormat("dd.MM.YYYY").parse(date);
        } catch (ParseException ex)
        {
            //On fait ce qu'on veut
            throw new JSONException("Bad date format : " + date);
        }
    }

    public String getsInitialJson(){
        return sInitialJson;
    }

    public Date getmDate() {
        return mDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getTmp() {
        return tmp;
    }

    public void setTmp(Integer tmp) {
        this.tmp = tmp;
    }

    public Integer getWndSpd() {
        return wndSpd;
    }

    public void setWndSpd(Integer wndSpd) {
        this.wndSpd = wndSpd;
    }

    public Integer getWndGust() {
        return wndGust;
    }

    public void setWndGust(Integer wndGust) {
        this.wndGust = wndGust;
    }

    public String getWndDir() {
        return wndDir;
    }

    public void setWndDir(String wndDir) {
        this.wndDir = wndDir;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionKey() {
        return conditionKey;
    }

    public void setConditionKey(String conditionKey) {
        this.conditionKey = conditionKey;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconBig() {
        return iconBig;
    }

    public void setIconBig(String iconBig) {
        this.iconBig = iconBig;
    }
}

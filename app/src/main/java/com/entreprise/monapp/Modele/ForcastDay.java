package com.entreprise.monapp.Modele;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForcastDay {
    private String date;
    private String dayShort;
    private String dayLong;
    private Integer tmin;
    private Integer tmax;
    private String condition;
    private String conditionKey;
    private String icon;
    private String iconBig;
    private List<HourlyData> lsHourlyData;
    private String sInitialJson;
    private CityInfo mCityInfo;

    public ForcastDay(String sJson) throws JSONException {
        lsHourlyData = new ArrayList<HourlyData>();

        sInitialJson = sJson;

        JSONObject jObject = new JSONObject(sJson);
        date = jObject.getString("date");
        dayShort = jObject.getString("day_short");
        dayLong = jObject.getString("day_long");
        tmin = jObject.getInt("tmin");
        tmax = jObject.getInt("tmax");
        condition = jObject.getString("condition");
        icon = jObject.getString("icon");

        if (jObject.has("city_info"))
            mCityInfo = new CityInfo(jObject.getString("city_info"));

        if (jObject.has("hourly_data")) {
            JSONObject jObjectHourlyData = jObject.getJSONObject("hourly_data");
            int i = 0;
            while (jObjectHourlyData.has(i + "H00")) {
                HourlyData d = new HourlyData(jObjectHourlyData.getJSONObject(i + "H00").toString());
                lsHourlyData.add(d);

                i++;
            }
        }
    }

    public String serrializeToJson() throws JSONException {
        JSONObject jObject = new JSONObject();

        jObject.put("date", date);
        jObject.put("day_short", dayShort);
        jObject.put("day_long", dayLong);
        jObject.put("tmin", tmin);
        jObject.put("tmax", tmax);
        jObject.put("condition", condition);
        jObject.put("condition_key", conditionKey);
        jObject.put("icon", icon);
        jObject.put("icon_big", iconBig);
        jObject.put("city_info", mCityInfo.getsInitialJson());

        return jObject.toString();
    }

    public CityInfo getCityInfo() {
        return mCityInfo;
    }

    public String getInitialJson(){
        return sInitialJson;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.mCityInfo = cityInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayShort() {
        return dayShort;
    }

    public void setDayShort(String dayShort) {
        this.dayShort = dayShort;
    }

    public String getDayLong() {
        return dayLong;
    }

    public void setDayLong(String dayLong) {
        this.dayLong = dayLong;
    }

    public Integer getTmin() {
        return tmin;
    }

    public void setTmin(Integer tmin) {
        this.tmin = tmin;
    }

    public Integer getTmax() {
        return tmax;
    }

    public void setTmax(Integer tmax) {
        this.tmax = tmax;
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

    public List<HourlyData> getHourlyData() {
        return lsHourlyData;
    }

    public void setHourlyData(List<HourlyData> hourlyData) {
        this.lsHourlyData = hourlyData;
    }
}

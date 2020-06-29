package com.entreprise.monapp.Modele;

import android.app.job.JobInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DonneesMeteo {
    private CityInfo cityInfo;
    private CurrentCondition currentCondition;
    private List<ForcastDay> lsForcastDay;

    public DonneesMeteo(String sJson) throws JSONException {
        lsForcastDay = new ArrayList<ForcastDay>();

        JSONObject jObject = new JSONObject(sJson);
        cityInfo = new CityInfo(jObject.getJSONObject("city_info").toString());
        currentCondition = new CurrentCondition(jObject.getJSONObject("current_condition").toString());

        int i = 0;
        while(jObject.has("fcst_day_" + i)){
            ForcastDay d = new ForcastDay(jObject.getJSONObject("fcst_day_" + i).toString());
            lsForcastDay.add(d);

            i++;
        }
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(CurrentCondition currentCondition) {
        this.currentCondition = currentCondition;
    }

    public List<ForcastDay> getFcstDay() {
        return lsForcastDay;
    }

    public void setFcstDay(List<ForcastDay> fcstDay0) {
        this.lsForcastDay = fcstDay0;
    }
}

package com.ylj.sunshine.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ylj on 6/25/15.
 * {"temp":{"day":27.58,"min":12.39,"max":27.58,"night":12.39,"eve":21.95,"morn":17.39}}
 */
public class Temperature {
    double day;
    double min;
    double max;
    double night;
    double eve;
    double morn;
    public void setDay(double day) {
        this.day = day;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }
    public static Temperature Create(JSONObject jsonObject) {
        Temperature temp = new Temperature();
        try {
            temp.setDay(jsonObject.getDouble("day"));
            temp.setEve(jsonObject.getDouble("eve"));
            temp.setMin(jsonObject.getDouble("min"));
            temp.setMax(jsonObject.getDouble("max"));
            temp.setNight(jsonObject.getDouble("night"));
            temp.setMorn(jsonObject.getDouble("morn"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }
}

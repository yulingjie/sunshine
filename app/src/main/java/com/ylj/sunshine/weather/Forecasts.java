package com.ylj.sunshine.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylj on 6/25/15.
 */
public class Forecasts {
    private Location location;
    private int cnt;
    private Forecast[] forecasts;
    public void setForecasts(Forecast[] forecasts) {
        this.forecasts = forecasts;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
    public static Forecasts Create(String jsonStr) {
        Forecasts casts = new Forecasts();
        JSONObject json = null;
        try {
            json = new JSONObject(jsonStr);
            Location loc = new Location();
            loc.setCode(json.getInt("cod"));
            loc.setCity(City.Create(json.getJSONObject("city")));
            loc.setMessage(json.getDouble("message"));
            casts.setLocation(loc);
            casts.setCnt(json.getInt("cnt"));
            JSONArray forecastObjs = json.getJSONArray("list");
            List<Forecast> forecastList = new ArrayList<Forecast>();
            for(int i =0; i != forecastObjs.length(); ++i)
            {
                JSONObject forecastObj = forecastObjs.getJSONObject(i);
                forecastList.add(Forecast.Create(forecastObj));
            }
            casts.setForecasts(forecastList.toArray(new Forecast[0]));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return casts;
    }
}

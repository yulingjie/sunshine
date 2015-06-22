package com.example.ylj.sunshine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylj on 6/20/15.
 * example Forecasts:
 * {Location}, "cnt": 0, "list":[{Forecast},{Forecast}]
 */
public class Forecasts {
    Location location;

    int cnt;
    Forecast[] forecasts;

    public static Forecasts Create(String jsonStr) {
        Forecasts casts = new Forecasts();
        try {
            JSONArray jsonObjs = new JSONArray(jsonStr);
            JSONObject locationObj = (JSONObject) jsonObjs.get(0);
            casts.setLocation(Location.Create(locationObj));
            JSONObject countObj = jsonObjs.getJSONObject(1);
            casts.setCnt(countObj.getInt("cnt"));
            JSONObject forecastsListObj = jsonObjs.getJSONObject(2);
            JSONArray forecastObjs = forecastsListObj.getJSONArray("list");
            List<Forecast> forecastList = new ArrayList<Forecast>();
            for (int i = 0; i != forecastObjs.length(); ++i) {
                JSONObject forecastObj = forecastObjs.getJSONObject(i);
                forecastList.add(Forecast.Create(forecastObj));
            }

            casts.setForecasts(forecastList.toArray(new Forecast[0]));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return casts;
    }

    public void setForecasts(Forecast[] forecasts) {
        this.forecasts = forecasts;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}

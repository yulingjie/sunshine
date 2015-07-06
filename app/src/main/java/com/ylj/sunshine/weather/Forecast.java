package com.ylj.sunshine.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylj on 6/25/15.
 */
public class Forecast{
    long dt;
    Temperature temp;
    double pressure;
    int humidity;
    Weather[] weather;
    double speed;
    int deg;
    int clouds;
    double rain;

    public void setDt(long dt) {
        this.dt = dt;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }
    public Weather[] getWeather()
    {
        return weather;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }
    public static Forecast Create(JSONObject jsonObject) {
        Forecast forecast = new Forecast();
        try {
            forecast.setDt(jsonObject.getLong("dt"));
            JSONObject tempObj = jsonObject.getJSONObject("temp");
            forecast.setTemp(Temperature.Create(tempObj));
            forecast.setPressure(jsonObject.getDouble("pressure"));
            forecast.setHumidity(jsonObject.getInt("humidity"));
            List<Weather> weatherList = new ArrayList<Weather>();
            JSONArray weatherObjs = jsonObject.getJSONArray("weather");
            for (int i = 0; i != weatherObjs.length(); ++i) {
                JSONObject weatherObj = weatherObjs.getJSONObject(i);
                weatherList.add(Weather.Create(weatherObj));
            }
            forecast.setWeather(weatherList.toArray(new Weather[0]));
            forecast.setSpeed(jsonObject.getDouble("speed"));
            forecast.setDeg(jsonObject.getInt("deg"));
            forecast.setClouds(jsonObject.getInt("clouds"));
            forecast.setRain(jsonObject.optDouble("rain", 0));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecast;
    }

}
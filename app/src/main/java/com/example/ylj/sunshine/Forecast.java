package com.example.ylj.sunshine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylj on 6/20/15.
 * example Forecast JsonObject :
 * {"dt":1434819600,
 * {temperature},"pressure":990.23,"humidity":89,
 * {Weather},"speed":4.61,"deg":187,"clouds":36,"rain":1.17}
 */
public class Forecast {

    long dt;
    Temperature temp;
    double pressure;
    int humidity;
    Weather[] weather;
    double speed;
    int deg;
    int clouds;
    double rain;

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
            forecast.setRain(jsonObject.getDouble("rain"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecast;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
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

    /**
     * example json for Temperature
     * "temp":{"day":25.83,"min":18.16,"max":25.83,"night":19.48,"eve":22.17,"morn":18.16}
     */
    static class Temperature {
        double day;
        double min;
        double max;
        double night;
        double eve;
        double morn;

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

    }

    /**
     * example json for Weather
     * "weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}]
     */
    static class Weather {
        int id;
        String main;
        String desc;
        String icon;

        public static Weather Create(JSONObject jsonObject) {
            Weather weather = new Weather();
            try {
                weather.setId(jsonObject.getInt("id"));
                weather.setMain(jsonObject.getString("main"));
                weather.setDesc(jsonObject.getString("description"));
                weather.setIcon(jsonObject.getString("icon"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }


    }
}

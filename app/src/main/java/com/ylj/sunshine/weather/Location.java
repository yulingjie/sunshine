package com.ylj.sunshine.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ylj on 6/25/15.
 * {"cod":"200","message":0.0357,"city":{"id":0,"name":"Mountain View","country":"US","coord":{"lat":37.4056,"lon":-122.0775}}
 */
public class Location {
    private int code;
    private double message;
    private City city;


    public void setCity(City city) {
        this.city = city;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(double message) {
        this.message = message;
    }
    public static Location Create(JSONObject jsonObj)
    {
        Location location = new Location();

        try {
            location.setCode(jsonObj.getInt("cod"));
            location.setMessage(jsonObj.getDouble("message"));
            // parse city object
            JSONObject cityObj = jsonObj.getJSONObject("city");
            location.setCity(City.Create(cityObj));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return location;
    }
}

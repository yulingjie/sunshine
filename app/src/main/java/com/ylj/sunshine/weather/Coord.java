package com.ylj.sunshine.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ylj on 6/25/15.
 */

/**
 * example for Coord:
 *      "coord":{"lat":37.4056,"lon":-122.0775}
 */
public class Coord {
    double lon;
    double lat;

    public void setLon(double lon)
    {
        this.lon = lon;
    }
    public void setLat(double lat)
    {
        this.lat = lat;
    }
    public static Coord Create(JSONObject jsonObject) {
        Coord coord = new Coord();
        try {
            coord.setLon(jsonObject.getDouble("lon"));
            coord.setLat(jsonObject.getDouble("lat"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return coord;
    }
}

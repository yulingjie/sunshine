package com.ylj.sunshine.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ylj on 6/25/15.
 * example json
 * city":{"id":0,"name":"Mountain View","country":"US","coord":{"lat":37.4056,"lon":-122.0775}}
 */
public class City {
    long id;
    String name;
    Coord coord;
    String country;
    long population;
    //Sys sys;
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /*public void setPopulation(long population) {
        this.population = population;
    }*/

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /*public void setSys(Sys sys) {
        this.sys = sys;
    }*/
    public static City Create(JSONObject jsonObject)
    {
        City ct = new City();
        try {
            ct.setId(jsonObject.getLong("id"));
            ct.setName(jsonObject.getString("name"));
            JSONObject coordObj = jsonObject.getJSONObject("coord");
            ct.setCoord(Coord.Create(coordObj));
            ct.setCountry(jsonObject.getString("country"));
            //ct.setPopulation(jsonObject.getLong("population"));
           // JSONObject sysObj = jsonObject.getJSONObject("sys");
            //ct.setSys(Sys.Create(sysObj));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ct;
    }
}

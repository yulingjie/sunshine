package com.ylj.sunshine.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ylj on 6/25/15.
 */
public class Sys {
    long population;

    public void setPopulation(long population)
    {
        this.population = population;
    }
    public static Sys Create(JSONObject jsonObject)
    {
        Sys sys = new Sys();
        try {
            sys.setPopulation(jsonObject.getLong("population"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sys;
    }
}

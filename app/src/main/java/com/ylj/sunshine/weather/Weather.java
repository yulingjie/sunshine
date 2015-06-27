package com.ylj.sunshine.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ylj on 6/25/15.
 */
public class Weather {
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

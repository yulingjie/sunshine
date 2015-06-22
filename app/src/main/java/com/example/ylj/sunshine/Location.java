package com.example.ylj.sunshine;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ylj on 6/20/15.
 */
public class Location {
    int code;
    String message;

    City city;

    /*

         */
    public static Location Create(JSONObject jsonObj)
    {
        Location location = new Location();

        try {
            location.setCode(jsonObj.getInt("cod"));
            location.setMessage(jsonObj.getString("message"));
            // parse city object
            JSONObject cityObj = jsonObj.getJSONObject("city");
           location.setCity(City.Create(cityObj));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return location;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*
    example city json object :
     city":{"id":6058560,"name":"London",
        "coord":
            {"lon":-81.23304,"lat":42.983391},
        "country":"CA",
        "population":0,
        "sys":
            {"population":0}
           }
     */
    static class City
    {
        long id;
        String name;
        Coord coord;
        String country;
        long population;
        Sys sys;

        public static City Create(JSONObject jsonObject)
        {
            City ct = new City();
            try {
                ct.setId(jsonObject.getLong("id"));
                ct.setName(jsonObject.getString("name"));
                JSONObject coordObj = jsonObject.getJSONObject("coord");
                ct.setCoord(Coord.Create(coordObj));
                ct.setCountry(jsonObject.getString("country"));
                ct.setPopulation(jsonObject.getLong("population"));
               JSONObject sysObj = jsonObject.getJSONObject("sys");
               ct.setSys(Sys.Create(sysObj));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return ct;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setPopulation(long population) {
            this.population = population;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public void setSys(Sys sys) {
            this.sys = sys;
        }
    }

    /**
     *
        "sys":
            {"population":0}
           }
     */
    static class Sys
    {
        long population;

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

        public void setPopulation(long population) {
            this.population = population;
        }
    }

    /**
     *"coord":{"lon":-81.23304,"lat":42.983391}
     */
    static class Coord
    {
        double lon;
        double lat;

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

        public void setLon(double lon) {
            this.lon = lon;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}

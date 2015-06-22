package com.example.ylj.sunshine;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylj on 6/17/15.
 */
public class ForecastFragment extends Fragment {

    static final String TAG= "ForecastFagment";
    private String[] strs;
    private String jsonStr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<String> weatherList = new ArrayList<String>();
        weatherList.add("ShangHai, 5-15, Sun");
        weatherList.add("BeiJing, 15-20, Cloudy");
        weatherList.add("ChengDu, 20-25, Sun");
        weatherList.add("Tianjing, 10-15, rainy");
        weatherList.add("JingDeZheng, 20-27, sunny");


        strs = weatherList.toArray(new String[0]);

        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // test code , maybe buggy
        ListView lv = (ListView) view.findViewById(R.id.listView);

        ListAdapter la = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strs);
        lv.setAdapter(la);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh: {
                FetchWeatherTask task = new FetchWeatherTask();
                task.execute("http://api.openweathermap.org/data/2.5/forecast/daily?q=London&units=metric&cnt=7");
            }
            break;
        }
        return true;
    }

    class FetchWeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(String str) {

            jsonStr = str;
            Log.v(TAG, jsonStr);
            super.onPostExecute(str);
        }

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return null;
        }
    }

}


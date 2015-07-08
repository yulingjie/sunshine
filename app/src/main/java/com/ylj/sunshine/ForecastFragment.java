package com.ylj.sunshine;

import android.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ylj.sunshine.R;
import com.ylj.sunshine.weather.Forecast;
import com.ylj.sunshine.weather.Forecasts;
import com.ylj.sunshine.weather.Temperature;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by ylj on 6/17/15.
 */
public class ForecastFragment extends Fragment {

    static final String TAG= "ForecastFagment";
    private List<String> strs;
    ArrayAdapter<String> forecastAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        strs = new ArrayList<>();
        strs.add("ShangHai, 5-15, Sun");
        strs.add("BeiJing, 15-20, Cloudy");
        strs.add("ChengDu, 20-25, Sun");
        strs.add("Tianjing, 10-15, rainy");
        strs.add("JingDeZheng, 20-27, sunny");



        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // test code , maybe buggy
        ListView lv = (ListView) view.findViewById(R.id.listView);

        forecastAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, strs);
        lv.setAdapter(forecastAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(ForecastFragment.this.getActivity(),
                       forecastAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
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
                task.execute("London","metric","7");
            }
            break;
        }
        return true;
    }

    class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

        final static String scheme = "http";
        final static String dataPath = "data";
        final static String numPath = "2.5";
        final static String fcPath = "forecast";
        final static String dailyPath = "daily";
        final static String authority = "api.openweathermap.org";
        final static String keyUnit = "unit";
        final static String keyCnt = "cnt";
        final static String keyPostcode = "q";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String[] str) {
            super.onPostExecute(str);

            forecastAdapter.clear();
            forecastAdapter.addAll(str);
        }

        @Override
        protected String[] doInBackground(String... params) {
            String postcode = params[0];
            String unit = params[1];
            String cnt = params[2];
            Uri.Builder uriBuilder = new Uri.Builder();
            uriBuilder.scheme(scheme)
                    .authority(authority)
                    .appendPath(dataPath)
                    .appendPath(numPath)
                    .appendPath(fcPath)
                    .appendPath(dailyPath)
                    .appendQueryParameter(keyPostcode, postcode)
                    .appendQueryParameter(keyUnit, unit)
                    .appendQueryParameter(keyCnt, cnt);


            HttpURLConnection urlConnection = null;
            String weatherStr = null;
            try {
                URL url = new URL(uriBuilder.build().toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                weatherStr = builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            if(weatherStr != null)
            {
                return parseForecasts(weatherStr);
            }
            return null;
        }
        private String getReadableDateString(long time){
            // Because the API returns a unix timestamp (measured in seconds),
            // it must be converted to milliseconds in order to be converted to valid date.
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd", Locale.getDefault());
            return shortenedDateFormat.format(time);
        }
        private String[] parseForecasts(String weatherStr) {
            Forecasts forecasts = Forecasts.Create(weatherStr);
            List<String> result = new ArrayList<String>();
            Forecast[] forecastArray = forecasts.getForecasts();



            Calendar calendar = new GregorianCalendar(TimeZone.getDefault(),Locale.getDefault());
            calendar.setTimeInMillis(System.currentTimeMillis());
            int startDay = calendar.get(Calendar.DAY_OF_YEAR);
            for(int i =0; i != forecastArray.length; ++i)
            {
                Forecast forecast = forecastArray[i];
                long dateTime;
                calendar.set(Calendar.DAY_OF_YEAR, startDay + i);

                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd", Locale.getDefault());
                String day = format.format(calendar.getTime());
                String desc = forecast.getWeather()[0].getDesc();
                Temperature temp = forecast.getTemp();
                String highAndLow = formatHighLows(temp.getMin(), temp.getMax());
                result.add(day + "-" + desc + "-" + highAndLow);
            }
            return result.toArray(new String[0]);
        }
        private String formatHighLows(double high, double low) {
            // For presentation, assume the user doesn't care about tenths of a degree.
            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);

            String highLowStr = roundedHigh + "/" + roundedLow;
            return highLowStr;
        }

    }

}


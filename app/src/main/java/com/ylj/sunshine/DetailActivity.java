package com.ylj.sunshine;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    public static String DETAIL_EXTRA = "com.ylj.sunshine.DetailActivity.forecast";

    String forecast;

    ShareActionProvider shareActionProvider = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        forecast = intent.getStringExtra(Intent.EXTRA_TEXT);
        Fragment fragment = new PlaceHolderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DETAIL_EXTRA, forecast);
        fragment.setArguments(bundle);
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        shareActionProvider.setShareIntent(createIntent());
        shareActionProvider.setOnShareTargetSelectedListener(new ShareActionProvider.OnShareTargetSelectedListener() {
            @Override
            public boolean onShareTargetSelected(ShareActionProvider source, Intent intent) {

                return false;
            }
        });
        return true;
    }

    Intent createIntent() {
        Intent forecastIntent = new Intent(Intent.ACTION_SEND);
        forecastIntent.setType("text/plain");
        forecastIntent.putExtra(Intent.EXTRA_TEXT, forecast);
        forecastIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        return forecastIntent;
    }

    Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.default_weather));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        intent.setType("text/plain");
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings: {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.menu_item_share: {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, forecast);
                intent.setType("text/plain");
                shareActionProvider.setShareIntent(intent);
            }
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceHolderFragment extends Fragment {

        public PlaceHolderFragment() {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_detail, container, false);
            TextView textView = (TextView) view.findViewById(R.id.detail_text);
            Bundle bundle = this.getArguments();
            String forecast = bundle.getString(DetailActivity.DETAIL_EXTRA);
            textView.setText(forecast);
            return view;
        }
    }
}

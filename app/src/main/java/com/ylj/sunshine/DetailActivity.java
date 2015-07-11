package com.ylj.sunshine;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    public static String DETAIL_EXTRA="com.ylj.sunshine.DetailActivity.forecast";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String forecast = intent.getStringExtra(Intent.EXTRA_TEXT);
        Fragment fragment = new PlaceHolderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DETAIL_EXTRA,forecast);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
   public static class PlaceHolderFragment extends Fragment {

       public PlaceHolderFragment()
       {

       }
       @Nullable
       @Override
       public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.fragment_detail,container, false);
           TextView textView = (TextView) view.findViewById(R.id.detail_text);
           Bundle bundle = this.getArguments();
           String forecast = bundle.getString(DetailActivity.DETAIL_EXTRA);
           textView.setText(forecast);
           return view;
       }
   }
}

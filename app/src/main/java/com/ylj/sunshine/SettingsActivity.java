package com.ylj.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity
    implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_key)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        onPreferenceChange(findPreference(key),sharedPreferences.getString(key, ""));
    }

    private void bindPreferenceSummaryToValue(Preference preference)
    {
        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference, PreferenceManager
                .getDefaultSharedPreferences(preference.getContext())
        .getString(preference.getKey(),""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if(preference instanceof ListPreference)
        {
            return true;
        }
        else
        {
            preference.setSummary((String) newValue);

            preference.setDefaultValue(newValue);
            return true;
        }
    }
}

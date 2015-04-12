package com.example.sampleapplication.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sampleapplication.SampleApplication;
import com.example.sampleapplication.model.Request;

public class PreferencesImpl implements Preferences {

    private static final String PREFERENCES_NAME = "sample_prefs";
    private static final String PREF_NAME = "name";

    private final SharedPreferences prefs;

    public PreferencesImpl(Context context) {
        this.prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Request getLastRequest() {
        final String name = prefs.getString(PREF_NAME, null);
        return new Request(name);
    }

    @Override
    public void clear() {
        final SharedPreferences.Editor editor = prefs.edit();
        editor.remove(PREF_NAME);
        editor.apply();
    }

    @Override
    public void setLastNameUsed(String name) {
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_NAME, name);
        editor.apply();
    }

}

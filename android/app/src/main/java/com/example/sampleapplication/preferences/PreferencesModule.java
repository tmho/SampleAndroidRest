package com.example.sampleapplication.preferences;

import android.content.Context;

import com.example.sampleapplication.SampleApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

    Context context;

    public PreferencesModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Preferences providePreferences() {
        return new PreferencesImpl(context);
    }
}

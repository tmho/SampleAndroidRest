package com.example.sampleapplication;

import android.app.Application;

import com.example.sampleapplication.api.ApiModule;
import com.example.sampleapplication.preferences.PreferencesModule;
import com.facebook.stetho.Stetho;

import javax.inject.Singleton;

import dagger.Component;


public class SampleApplication extends Application {

    @Singleton
    @Component(modules = {PreferencesModule.class, ApiModule.class})
    public interface ApplicationComponent {
        void inject(SampleApplication application);

        void inject(MainActivity mainActivity);
    }

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerSampleApplication_ApplicationComponent.builder()
                .preferencesModule(new PreferencesModule(this))
                .build();

        component.inject(this);

        //checkout: chrome://inspect
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

    public ApplicationComponent component() {
        return component;
    }

}

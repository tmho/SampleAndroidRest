package com.example.sampleapplication.api;

import com.example.sampleapplication.model.json.GsonFactory;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint("http://10.0.0.2:3000");
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Client provideClient(OkHttpClient client) {
        client.networkInterceptors().add(new StethoInterceptor());
        return new OkClient(client);
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(GsonFactory.getGson()))
                .build();
    }

    @Provides
    @Singleton
    SaySomethingService provideSaySomethingService(RestAdapter restAdapter) {
        return restAdapter.create(SaySomethingService.class);
    }

}

package com.example.sampleapplication.model.json;

import com.google.gson.Gson;

public class GsonFactory {
    private static Gson gson;

    public synchronized static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
package com.example.sampleapplication.preferences;

import com.example.sampleapplication.model.Request;

public interface Preferences {
    public Request getLastRequest();

    public void clear();

    public void setLastNameUsed(String name);
}

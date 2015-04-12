package com.example.sampleapplication.preferences;

import android.test.AndroidTestCase;

import java.util.UUID;

public class PreferencesImplTest extends AndroidTestCase {
    private Preferences preferences;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        preferences = new PreferencesImpl(getContext());
    }

    public void testGetAndSetLastUsedName() throws Exception {
        String uniqueName = UUID.randomUUID().toString();
        preferences.setLastNameUsed(uniqueName);
        assertEquals(uniqueName, preferences.getLastRequest().getName());
    }

    public void testClear() throws Exception {
        if (preferences.getLastRequest().getName() == null) {
            preferences.setLastNameUsed("testData");
        }
        preferences.clear();
        assertEquals(null, preferences.getLastRequest().getName());
    }

}
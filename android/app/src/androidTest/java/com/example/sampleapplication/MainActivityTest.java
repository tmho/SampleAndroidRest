package com.example.sampleapplication;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testButtonGetsDisabledWhenNoInputIsPresent() {
        onView(withId(R.id.submit_button)).check(ViewAssertions.matches(not(ViewMatchers.isEnabled())));
        onView(withId(R.id.name_input)).perform(typeText("Hello"));
        onView(withId(R.id.submit_button)).check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }
}
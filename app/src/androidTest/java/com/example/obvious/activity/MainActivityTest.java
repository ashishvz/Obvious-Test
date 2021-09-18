package com.example.obvious.activity;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.obvious.R;
import com.example.obvious.adapter.Adapter;

import junit.framework.TestCase;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest extends TestCase {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(
            MainActivity.class
    );

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void recyclerTest() {
        onView(ViewMatchers.withId(R.id.imageRecycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
        intended(hasComponent("com.example.obvious.activity.ImageDetailActivity"));
    }

}
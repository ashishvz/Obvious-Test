package com.example.obvious.activity;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.times;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;

import com.example.obvious.R;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SplashscreenTest extends TestCase {

    @Rule
    public ActivityScenarioRule<Splashscreen> activityScenarioRule = new ActivityScenarioRule<Splashscreen>(
            Splashscreen.class);

    @Rule
    public IntentsTestRule<Splashscreen> intentsTestRule = new IntentsTestRule<>(Splashscreen.class);

    @Test
    public void testBtn() {
        onView(withId(R.id.cardViewButton)).perform(click());
    }

    @Test
    public void checkForIntentChange() {
        onView(withId(R.id.cardViewButton)).perform(click());
        intended(hasComponent("com.example.obvious.activity.MainActivity"));
    }
}
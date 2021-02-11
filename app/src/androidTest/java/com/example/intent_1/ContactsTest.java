package com.example.intent_1;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Intent.ACTION_DIAL;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class ContactsTest extends TestCase {

    @Rule
    public IntentsTestRule<ContactsActivity> mActivityRule = new IntentsTestRule<>(
            ContactsActivity.class);

    private static String phoneNumber = "1234";
    private static final Uri INTENT_DATA_PHONE_NUMBER = Uri.parse("tel:" + phoneNumber);
    private static String PACKAGE_ANDROID_DIALER = "com.android.dialer";

    @Test
    public void testDialerIntent() {
        Intent resultData = new Intent();
        resultData.putExtra("phone", phoneNumber);
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        intending(toPackage("com.android.phone")).respondWith(result);
        onView(withId(R.id.e)).perform(typeText(phoneNumber));
        onView(withId(R.id.button)).perform(click());
        intended(allOf(
                hasAction(ACTION_DIAL),
                hasData(INTENT_DATA_PHONE_NUMBER),
                toPackage(PACKAGE_ANDROID_DIALER)));
    }
}
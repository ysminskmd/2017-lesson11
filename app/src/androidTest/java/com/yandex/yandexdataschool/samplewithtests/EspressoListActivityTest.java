package com.yandex.yandexdataschool.samplewithtests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class EspressoListActivityTest {

    @Rule
    public ActivityTestRule<TestListActivity> mListActivityRule = new ActivityTestRule<>(TestListActivity.class);

    @Test
    public void testDefaultTitle() {
        onView(allOf(isAssignableFrom(TextView.class),
                withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText("ListActivity")));
    }

    @Test
    public void testEvenNumber() {
        onData(equalTo("4")).perform(click());
        onView(allOf(isAssignableFrom(TextView.class),
                withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText("4")));
    }

    @Test
    public void testOddNumber() {
        onData(equalTo("5")).perform(click());
        onView(allOf(isAssignableFrom(TextView.class),
                withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText("-5")));
    }

}
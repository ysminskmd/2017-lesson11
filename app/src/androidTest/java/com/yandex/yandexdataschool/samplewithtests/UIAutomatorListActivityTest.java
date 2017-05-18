package com.yandex.yandexdataschool.samplewithtests;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class UIAutomatorListActivityTest {

    public static final String PACKAGE_NAME = "com.yandex.yandexdataschool.samplewithtests";
    public static final int TIMEOUT = 5000;
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage).isNotNull();
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = new Intent();
        intent.setClassName(PACKAGE_NAME, TestListActivity.class.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)),
                TIMEOUT);
    }

    @Test
    public void testOdd() throws UiObjectNotFoundException {
        mDevice.findObject(new UiSelector().text("3")).click();
        mDevice.wait(Until.hasObject(By.textContains("-3")), 1000);
        assertThat(mDevice.findObject(new UiSelector().textContains("-3")).exists()).isTrue();
    }

    @Test
    public void testEvent() throws UiObjectNotFoundException {
        mDevice.findObject(new UiSelector().text("4")).click();
        mDevice.wait(Until.hasObject(By.textContains("4")), 1000);
        assertThat(mDevice.findObject(new UiSelector().textContains("4")).exists()).isTrue();
    }

    @Test
    public void testNoClicks() {
        assertThat(mDevice.findObject(new UiSelector().textContains("ListActivity")).exists()).isTrue();
    }

}

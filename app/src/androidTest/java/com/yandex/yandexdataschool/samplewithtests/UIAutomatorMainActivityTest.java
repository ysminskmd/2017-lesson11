package com.yandex.yandexdataschool.samplewithtests;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class UIAutomatorMainActivityTest {

    public static final String PACKAGE_NAME = "com.yandex.yandexdataschool.samplewithtests";
    public static final int TIMEOUT = 100;
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)),
                TIMEOUT);
    }

    @Test
    public void testFloatingButton() throws UiObjectNotFoundException {
        UiObject fab = mDevice.findObject(new UiSelector()
                .resourceId("com.yandex.yandexdataschool.samplewithtests:id/fab")
                );
        fab.click();
        UiObject text = mDevice.findObject(new UiSelector().text("testtext"));
    }

}

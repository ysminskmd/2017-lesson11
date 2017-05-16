package com.yandex.yandexdataschool.samplewithtests.logic;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by avitenko on 16.5.17.
 */
@RunWith(RobolectricTestRunner.class)
public class LauncherApplicationsRetrieverTest {

    @Test
    public void testLauncherAppsIntent() {
        IntentFilter intent = new IntentFilter(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        assertThat(
                intent.match(
                        null,
                        new LauncherApplicationsRetriever(mock(PackageManager.class)).getResolvedIntent(),
                        false,
                        "testlog"
                ) & IntentFilter.MATCH_ADJUSTMENT_NORMAL).isGreaterThan(0);
    }

}
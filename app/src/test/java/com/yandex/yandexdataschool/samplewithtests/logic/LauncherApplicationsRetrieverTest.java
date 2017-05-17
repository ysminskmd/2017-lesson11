package com.yandex.yandexdataschool.samplewithtests.logic;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
public class LauncherApplicationsRetrieverTest {

    private static final IntentFilter LAUNCHER_FILTER = new IntentFilter(Intent.ACTION_MAIN);

    static {
        LAUNCHER_FILTER.addCategory(Intent.CATEGORY_LAUNCHER);
    }

    public static final String PACKAGE_NAME = "com.package";

    @Test
    public void testLauncherAppsIntent() {
        int matchResult = LAUNCHER_FILTER.match(
                null,
                new LauncherApplicationsRetriever(mock(PackageManager.class)).getResolvedIntent(),
                false,
                "testlog"
        );
        assertThat(matchResult).isGreaterThan(0);
        assertThat(matchResult & IntentFilter.MATCH_ADJUSTMENT_NORMAL).isNotZero();
    }

    @Test
    public void testLauncherApps() throws PackageManager.NameNotFoundException {
        PackageInfo info = new PackageInfo();
        info.versionName = "1.0.0";
        info.versionCode = 1;

        PackageManager manager = createPackageManagerWithOneActivity();

        doReturn(info).when(manager).getPackageInfo(eq(PACKAGE_NAME), anyInt());

        assertThat(new LauncherApplicationsRetriever(manager).retrieveApplications()).isNotEmpty();
    }

    private PackageManager createPackageManagerWithOneActivity() {
        ResolveInfo resolveInfo = new ResolveInfo();
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.packageName = PACKAGE_NAME;
        resolveInfo.activityInfo = activityInfo;
        List<ResolveInfo> resolvedActivities = new ArrayList<>();
        resolvedActivities.add(resolveInfo);


        PackageManager manager = mock(PackageManager.class);
        doReturn(resolvedActivities).when(manager).queryIntentActivities(argThat(new ArgumentMatcher<Intent>() {
            @Override
            public boolean matches(Intent argument) {
                return (LAUNCHER_FILTER.match(
                        null,
                        new LauncherApplicationsRetriever(mock(PackageManager.class)).getResolvedIntent(),
                        false,
                        "testlog"
                ) & IntentFilter.MATCH_ADJUSTMENT_NORMAL) != 0;
            }
        }), anyInt());
        return manager;
    }

    @Test
    public void testNameNotFoundException() throws PackageManager.NameNotFoundException {
        PackageManager manager = createPackageManagerWithOneActivity();
        doThrow(PackageManager.NameNotFoundException.class).when(manager).getPackageInfo(anyString(), anyInt());
        assertThat(new LauncherApplicationsRetriever(manager).retrieveApplications()).isNull();
    }

}
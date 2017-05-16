package com.yandex.yandexdataschool.samplewithtests.logic;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.yandex.yandexdataschool.samplewithtests.model.ApplicationDescription;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Created by avitenko on 16.5.17.
 */
public class AllApplicationsRetrieverTest {

    @Test
    public void testSystemCall() {
        PackageManager packageManager = mock(PackageManager.class);

        PackageInfo info = new PackageInfo();
        info.packageName = "com.package";
        info.versionName = "1.0.0";
        info.versionCode = 1;
        List<PackageInfo> list = new ArrayList<>(1);
        list.add(info);

        doReturn(list).when(packageManager).getInstalledPackages(anyInt());
        List<ApplicationDescription> descriptions = new AllApplicationsRetriever(packageManager).retrieveApplications();
        assertThat(descriptions.size()).isEqualTo(1);
        assertThat(descriptions).extracting("packageName", "versionName", "versionCode").containsOnly(
                tuple("com.package", "1.0.0", 1)
        );
    }

}
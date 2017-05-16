package com.yandex.yandexdataschool.samplewithtests.logic;

import android.content.pm.PackageManager;

import com.yandex.yandexdataschool.samplewithtests.model.ApplicationDescription;

import java.util.List;

/**
 * Created by avitenko on 16.5.17.
 */
public class AllApplicationsRetriever implements ApplicationRetriever {

    private final PackageManager mPackageManager;

    public AllApplicationsRetriever(PackageManager manager) {
        mPackageManager = manager;
    }

    @Override
    public List<ApplicationDescription> retrieveApplications() {
        return new PackageInfoConverter().convert(mPackageManager.getInstalledPackages(0));
    }
}

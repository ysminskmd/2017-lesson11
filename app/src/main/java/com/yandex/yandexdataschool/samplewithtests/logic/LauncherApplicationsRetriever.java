package com.yandex.yandexdataschool.samplewithtests.logic;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.yandex.yandexdataschool.samplewithtests.model.ApplicationDescription;

import java.util.ArrayList;
import java.util.List;

public class LauncherApplicationsRetriever implements ApplicationRetriever{

    private final PackageManager mPackageManager;
    private final Intent mResolvedIntent;

    public LauncherApplicationsRetriever(PackageManager manager) {
        mPackageManager = manager;
        mResolvedIntent = new Intent(Intent.ACTION_MAIN);
        mResolvedIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    }

    public Intent getResolvedIntent() {
        return mResolvedIntent;
    }

    @Override
    public List<ApplicationDescription> retrieveApplications() {
        List<ResolveInfo> resolveInfos = mPackageManager.queryIntentActivities(mResolvedIntent, 0);
        List<PackageInfo> packageInfos = new ArrayList<>(resolveInfos.size());
        for (ResolveInfo info : resolveInfos) {
            try {
                packageInfos.add(mPackageManager.getPackageInfo(info.activityInfo.packageName, 0));
            } catch (PackageManager.NameNotFoundException e) {
                return null;
            }
        }
        return new PackageInfoConverter().convert(packageInfos);
    }
}

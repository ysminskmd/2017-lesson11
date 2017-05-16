package com.yandex.yandexdataschool.samplewithtests.model;

/**
 * Created by avitenko on 16.5.17.
 */
public class ApplicationDescription {

    private final String mPackageName;
    private final String mVersionName;
    private final int mVersionCode;


    public ApplicationDescription(String name, String versionName, int versionCode) {
        mPackageName = name;
        mVersionName = versionName;
        mVersionCode = versionCode;
    }


    public String getPackageName() {
        return mPackageName;
    }

    public String getVersionName() {
        return mVersionName;
    }

    public int getVersionCode() {
        return mVersionCode;
    }
}

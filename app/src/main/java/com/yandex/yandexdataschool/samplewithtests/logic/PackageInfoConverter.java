package com.yandex.yandexdataschool.samplewithtests.logic;

import android.content.pm.PackageInfo;

import com.yandex.yandexdataschool.samplewithtests.model.ApplicationDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avitenko on 16.5.17.
 */

public class PackageInfoConverter {

    public List<ApplicationDescription> convert(Iterable<PackageInfo> packageInfos) {
        ArrayList<ApplicationDescription> descriptions = new ArrayList<>();
        for (PackageInfo info : packageInfos) {
            descriptions.add(new ApplicationDescription(
                    info.packageName, info.versionName, info.versionCode
            ));
        }
        return descriptions;
    }

}

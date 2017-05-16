package com.yandex.yandexdataschool.samplewithtests.logic;

import com.yandex.yandexdataschool.samplewithtests.model.ApplicationDescription;

import java.util.List;

/**
 * Created by avitenko on 16.5.17.
 */
public interface ApplicationRetriever {

    List<ApplicationDescription> retrieveApplications();

}

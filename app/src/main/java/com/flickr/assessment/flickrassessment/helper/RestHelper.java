package com.flickr.assessment.flickrassessment.helper;

import android.content.Context;

import com.flickr.assessment.flickrassessment.api.RestClient;
import com.flickr.assessment.flickrassessment.api.RestService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RestHelper {

    private RestService restService;

    @Inject
    public RestHelper(Context context) {
        restService = new RestClient().getService();
    }
}

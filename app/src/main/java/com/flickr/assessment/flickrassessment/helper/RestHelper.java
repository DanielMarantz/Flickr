package com.flickr.assessment.flickrassessment.helper;

import android.content.Context;

import com.flickr.assessment.flickrassessment.api.RestClient;
import com.flickr.assessment.flickrassessment.api.RestService;
import com.flickr.assessment.flickrassessment.model.Feed;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * RestHelper is a helper for Observable API endpoints.
 * <p>
 * NOTE: Helpers are injected into the presenters and should
 * never touch the view layer.
 */
@Singleton
public class RestHelper {

    private RestService restService;

    @Inject
    public RestHelper(Context context) {
        restService = new RestClient().getService();
    }

    public Observable<Feed> getPublicPhotos() {
        return restService.getPublicPhotos();
    }

    public Observable<Feed> searchPublicPhotos(final String tags) {
        return restService.searchPublicPhotos(tags);
    }
}

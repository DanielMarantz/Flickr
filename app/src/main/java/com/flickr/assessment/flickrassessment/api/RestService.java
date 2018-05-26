package com.flickr.assessment.flickrassessment.api;

import com.flickr.assessment.flickrassessment.model.Feed;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * RestService is the Retrofit REST service for API calls.
 */
public interface RestService {

    @GET("photos_public.gne")
    Observable<Feed> getPublicPhotos();
}
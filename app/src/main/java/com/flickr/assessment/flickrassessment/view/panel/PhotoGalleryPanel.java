package com.flickr.assessment.flickrassessment.view.panel;

import com.flickr.assessment.flickrassessment.model.Items;

/**
 * PhotoGalleryPanel interfaces the callback methods
 * for the PhotoGalleryFragment.
 * <p>
 * This is the communication technique for presentation layer to
 * the view layer.
 */
public interface PhotoGalleryPanel {

    void onRenderPhotos(Items[] photos);

    void onError();
}

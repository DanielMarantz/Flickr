package com.flickr.assessment.flickrassessment.presenter.main;

import com.flickr.assessment.flickrassessment.di.scope.PerSection;
import com.flickr.assessment.flickrassessment.helper.RestHelper;
import com.flickr.assessment.flickrassessment.presenter.base.BasePresenter;
import com.flickr.assessment.flickrassessment.view.panel.PhotoGalleryPanel;

import javax.inject.Inject;

@PerSection
public class PhotoGalleryPresenter extends BasePresenter<PhotoGalleryPanel> {

    private static final String TAG = PhotoGalleryPresenter.class.getSimpleName();

    private RestHelper mRestHelper;
    private PhotoGalleryPanel mPanel;

    @Inject
    public PhotoGalleryPresenter(RestHelper restHelper) {
        this.mRestHelper = restHelper;
    }

    @Override
    public void setPanel(PhotoGalleryPanel panel) {
        this.mPanel = panel;
    }

}

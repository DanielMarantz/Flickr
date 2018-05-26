package com.flickr.assessment.flickrassessment.presenter.main;

import android.util.Log;

import com.flickr.assessment.flickrassessment.di.scope.PerSection;
import com.flickr.assessment.flickrassessment.presenter.base.BasePresenter;
import com.flickr.assessment.flickrassessment.view.panel.PhotoGalleryPanel;

import java.util.Arrays;

import javax.inject.Inject;

@PerSection
public class PhotoGalleryPresenter extends BasePresenter<PhotoGalleryPanel> {

    private static final String TAG = PhotoGalleryPresenter.class.getSimpleName();

    private PhotoGalleryPanel mPanel;

    @Inject
    public PhotoGalleryPresenter() {

    }

    @Override
    public void setPanel(PhotoGalleryPanel panel) {
        this.mPanel = panel;
    }

}

package com.flickr.assessment.flickrassessment.presenter.main;

import android.util.Log;

import com.flickr.assessment.flickrassessment.di.scope.PerSection;
import com.flickr.assessment.flickrassessment.helper.RestHelper;
import com.flickr.assessment.flickrassessment.model.Feed;
import com.flickr.assessment.flickrassessment.model.Items;
import com.flickr.assessment.flickrassessment.presenter.base.BasePresenter;
import com.flickr.assessment.flickrassessment.view.panel.PhotoGalleryPanel;

import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    public void getPhotos() {

        mRestHelper.getPublicPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Feed>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        mPanel.onError();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onNext(Feed response) {
                        Log.d(TAG, "onNext: " + response.toString());
                        Items[] sortedItems = response.getItems();
                        Arrays.sort(sortedItems);
                        mPanel.onRenderPhotos(sortedItems);
                    }
                });
    }
}

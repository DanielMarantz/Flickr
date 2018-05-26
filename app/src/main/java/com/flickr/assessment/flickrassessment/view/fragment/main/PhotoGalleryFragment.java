package com.flickr.assessment.flickrassessment.view.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.flickr.assessment.flickrassessment.R;
import com.flickr.assessment.flickrassessment.presenter.main.PhotoGalleryPresenter;
import com.flickr.assessment.flickrassessment.view.activity.main.MainActivity;
import com.flickr.assessment.flickrassessment.view.fragment.base.BaseFragmentWithButterKnife;

import javax.inject.Inject;


public class PhotoGalleryFragment extends BaseFragmentWithButterKnife {

    @Inject
    PhotoGalleryPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialInjector();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialView();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_photo_gallery;
    }

    private void initialInjector() {
        ((MainActivity) getActivity()).getMainViewComponent().inject(this);
    }

    private void initialView() {

    }
}

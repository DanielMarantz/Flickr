package com.flickr.assessment.flickrassessment.view.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.flickr.assessment.flickrassessment.R;
import com.flickr.assessment.flickrassessment.model.Items;
import com.flickr.assessment.flickrassessment.presenter.main.PhotoGalleryPresenter;
import com.flickr.assessment.flickrassessment.view.activity.main.MainActivity;
import com.flickr.assessment.flickrassessment.view.adapter.PhotoGalleryAdapter;
import com.flickr.assessment.flickrassessment.view.fragment.base.BaseFragmentWithButterKnife;
import com.flickr.assessment.flickrassessment.view.panel.PhotoGalleryPanel;

import javax.inject.Inject;

import butterknife.BindView;


public class PhotoGalleryFragment extends BaseFragmentWithButterKnife implements PhotoGalleryPanel,
        PhotoGalleryAdapter.RecyclerClickListener {

    @Inject
    PhotoGalleryPresenter presenter;

    @BindView(R.id.photo_gallery_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.photo_gallery_no_results)
    CardView noResultsView;

    @BindView(R.id.photo_gallery_spinner)
    ProgressBar progressBar;

    private PhotoGalleryAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialInjector();
        initialPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialAdapter();
        initialView();
        presenter.getPhotos();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_photo_gallery;
    }

    private void initialInjector() {
        ((MainActivity) getActivity()).getMainViewComponent().inject(this);
    }

    private void initialPresenter() {
        presenter.setPanel(this);
    }

    private void initialAdapter() {
        mAdapter = new PhotoGalleryAdapter(this);
    }

    private void initialView() {

    }

    private void renderPhotos(Items[] photos) {
        if (photos != null && photos.length > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            noResultsView.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(mAdapter);
            mAdapter.setPhotos(photos);
            mAdapter.notifyDataSetChanged();
        } else {
            recyclerView.setVisibility(View.GONE);
            noResultsView.setVisibility(View.VISIBLE);
            showSnackBar(getView(), getString(R.string.no_search_results));
        }
    }

    private void showProgressSpinner(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onRenderPhotos(Items[] photos) {
        showProgressSpinner(false);
        renderPhotos(photos);
    }

    @Override
    public void onError() {
        showProgressSpinner(false);
        showSnackBar(getView(), getString(R.string.failed_to_fetch_photos));
    }

    @Override
    public void onShareClicked(int position) {

    }

    @Override
    public void onExploreClicked(int position) {

    }
}

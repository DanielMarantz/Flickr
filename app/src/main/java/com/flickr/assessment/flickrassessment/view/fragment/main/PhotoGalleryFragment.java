package com.flickr.assessment.flickrassessment.view.fragment.main;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

/**
 * PhotoGalleryFragment displays a Public Photo Gallery for the view layer.
 * <p>
 * MainViewComponent is built in the parent activity
 * and injected into the fragment.
 * <p>
 * Note: Fragments are the interaction with the user and the
 * view layer. User interactions are propagated to the presentation
 * layer for heavier processing.
 */
public class PhotoGalleryFragment extends BaseFragmentWithButterKnife implements PhotoGalleryPanel,
        PhotoGalleryAdapter.RecyclerClickListener {

    @Inject
    PhotoGalleryPresenter presenter;

    @BindView(R.id.photo_gallery_search_container)
    Toolbar searchContainerView;
    @BindView(R.id.photo_gallery_search)
    SearchView searchView;
    @BindView(R.id.photo_gallery_fab)
    FloatingActionButton fabView;
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
        initialSearchView();
        initialListeners();
    }

    private void initialSearchView() {
        searchView.setQueryHint(getString(R.string.search_tool_tip));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String tags) {
                showProgressSpinner(true);
                presenter.searchPhotos(tags);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initialListeners() {
        searchContainerView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
                searchView.requestFocus();
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
            }
        });
        fabView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
                searchView.requestFocus();
            }
        });
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

    private void sharePhotoByEmail(String photoLink) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        emailIntent.putExtra(Intent.EXTRA_TEXT, photoLink);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (ActivityNotFoundException e) {
            showSnackBar(getView(), getString(R.string.no_email_apps));
        }
    }

    private void openWebBrowser(String photoLink) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(photoLink)));
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
        Items pictureData = mAdapter.getPhoto(position);
        sharePhotoByEmail(pictureData.getLink());
    }

    @Override
    public void onExploreClicked(int position) {
        Items pictureData = mAdapter.getPhoto(position);
        openWebBrowser(pictureData.getLink());
    }
}

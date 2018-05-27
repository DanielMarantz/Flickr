package com.flickr.assessment.flickrassessment.presenter;

import com.flickr.assessment.flickrassessment.presenter.main.PhotoGalleryPresenter;
import com.flickr.assessment.flickrassessment.view.panel.PhotoGalleryPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class PhotoGalleryPresenterTest {

    private PhotoGalleryPresenter presenter;
    private PhotoGalleryPanel panel;

    @Before
    public void setUp() {
        presenter = mock(PhotoGalleryPresenter.class);
        panel = mock(PhotoGalleryPanel.class);
    }

    @After
    public void tearDown() {
        presenter = null;
        panel = null;
    }

    @Test
    public void setPanel() {
        doNothing().when(presenter).setPanel(any(PhotoGalleryPanel.class));
        presenter.setPanel(panel);
    }

    @Test
    public void requestPhotoGallery() {
        doNothing().when(presenter).getPhotos();
        presenter.getPhotos();
    }

    @Test
    public void requestSearchPhotos() {
        doNothing().when(presenter).searchPhotos("fun");
        presenter.searchPhotos("fun");
    }
}

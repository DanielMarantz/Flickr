package com.flickr.assessment.flickrassessment.view.activity.main;

import android.os.Bundle;

import com.flickr.assessment.flickrassessment.R;
import com.flickr.assessment.flickrassessment.di.component.DaggerMainViewComponent;
import com.flickr.assessment.flickrassessment.di.component.MainViewComponent;
import com.flickr.assessment.flickrassessment.di.module.RestModule;
import com.flickr.assessment.flickrassessment.view.activity.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private MainViewComponent mMainViewComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialInjector();
        initialView();
    }

    public void initialInjector() {
        mMainViewComponent = DaggerMainViewComponent.builder()
                .baseFlickrComponent(getMainApplication().getMainComponent())
                .restModule(new RestModule(this))
                .build();
        mMainViewComponent.inject(this);
    }

    public MainViewComponent getMainViewComponent() {
        return mMainViewComponent;
    }

    private void initialView() {

    }
}

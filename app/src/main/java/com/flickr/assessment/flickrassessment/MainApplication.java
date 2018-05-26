package com.flickr.assessment.flickrassessment;

import android.app.Application;

import com.flickr.assessment.flickrassessment.di.component.BaseFlickrComponent;
import com.flickr.assessment.flickrassessment.di.component.DaggerBaseFlickrComponent;
import com.flickr.assessment.flickrassessment.di.module.BaseFlickrModule;
import com.flickr.assessment.flickrassessment.di.module.MainApplicationModule;

public class MainApplication extends Application {

    private BaseFlickrComponent baseFlickrComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initialInjector();
    }

    /**
     * This method will implement BaseFlickrComponent which provides main
     * singleton dependencies in application life scope.
     */
    private void initialInjector() {
        baseFlickrComponent = DaggerBaseFlickrComponent.builder()
                .mainApplicationModule(new MainApplicationModule(this))
                .baseFlickrModule(new BaseFlickrModule(this))
                .build();
    }

    public BaseFlickrComponent getMainComponent() {
        return baseFlickrComponent;
    }
}

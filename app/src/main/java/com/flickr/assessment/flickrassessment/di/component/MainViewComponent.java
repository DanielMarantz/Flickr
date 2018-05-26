package com.flickr.assessment.flickrassessment.di.component;

import com.flickr.assessment.flickrassessment.di.scope.PerSection;
import com.flickr.assessment.flickrassessment.view.activity.main.MainActivity;

import dagger.Component;

/**
 * MainViewComponent will be used by Dagger to generate dependencies
 * for presentation layer.
 *
 * @Component: Used on an interface. This interface is used by Dagger 2
 * to generate code which uses the modules to fulfill the requested dependencies.
 */
@PerSection
@Component(dependencies = BaseFlickrComponent.class)

public interface MainViewComponent {
    void inject(MainActivity mainActivity);
}

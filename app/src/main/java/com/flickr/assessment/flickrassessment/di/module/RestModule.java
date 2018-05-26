package com.flickr.assessment.flickrassessment.di.module;

import android.content.Context;

import com.flickr.assessment.flickrassessment.di.scope.PerSection;
import com.flickr.assessment.flickrassessment.helper.RestHelper;

import dagger.Module;
import dagger.Provides;

/**
 * RestModule will be used by Dagger to implement MainViewComponent.
 *
 * @Module: Modules are classes whose methods provide dependencies, so we define a class and annotate it with @Module,
 * thus, Dagger will know where to find the dependencies in order to satisfy them when constructing class instances.
 * One important feature of modules is that they have been designed to be partitioned and composed together
 * for instance we will see that in apps we can have multiple composed modules.
 */
@Module
public class RestModule {

    private RestHelper restHelper;

    public RestModule(Context context) {
        this.restHelper = new RestHelper(context);
    }

    @Provides
    @PerSection
    public RestHelper providesRESTHelper() {
        return restHelper;
    }
}

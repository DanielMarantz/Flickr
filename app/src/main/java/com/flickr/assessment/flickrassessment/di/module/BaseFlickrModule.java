package com.flickr.assessment.flickrassessment.di.module;

import com.flickr.assessment.flickrassessment.MainApplication;

import dagger.Module;

/**
 * BaseFlickrModule will be providing application level (singleton) dependencies which can be
 * instanced when MainApplication get started.
 *
 * Dagger will use BaseFlickrModule to implement BaseFlickrComponent which actually takes charge of performing injection
 *
 * @Module: Modules are classes whose methods provide dependencies, so we define a class and annotate it with @Module,
 * thus, Dagger will know where to find the dependencies in order to satisfy them when constructing class instances.
 * One important feature of modules is that they have been designed to be partitioned and composed together
 * for instance we will see that in our apps we can have multiple composed modules.
 */
@Module
public class BaseFlickrModule {

    MainApplication application;

    public BaseFlickrModule(MainApplication application){
        this.application = application;
    }
}

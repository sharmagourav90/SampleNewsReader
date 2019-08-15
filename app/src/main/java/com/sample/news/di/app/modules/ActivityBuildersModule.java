package com.sample.news.di.app.modules;

import com.sample.news.di.news.module.NewsBuilderModule;
import com.sample.news.di.news.module.NewsModelsModule;
import com.sample.news.di.news.module.NewsModule;
import com.sample.news.di.news.scope.NewsScope;
import com.sample.news.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Dagger 2 injection in activities.
 */
@Module
public abstract class ActivityBuildersModule {
    @NewsScope
    @ContributesAndroidInjector(
            modules = {NewsBuilderModule.class, NewsModelsModule.class, NewsModule.class})
    abstract MainActivity contributeNewsActivity();
}

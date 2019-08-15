package com.sample.news.di.news.module;
import com.sample.news.ui.list.NewsListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Class responsible for injection in fragments of news screen.
 */
@Module
public abstract class NewsBuilderModule {
    @ContributesAndroidInjector
    abstract NewsListFragment contributeNewsListFragment();
}

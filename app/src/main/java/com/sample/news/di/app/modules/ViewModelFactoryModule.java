package com.sample.news.di.app.modules;

import com.sample.news.viewmodels.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

/**
 * Class responsible for injecting ViewModelFactory.
 */
@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);
}

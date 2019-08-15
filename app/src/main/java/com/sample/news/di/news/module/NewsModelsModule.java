package com.sample.news.di.news.module;

import androidx.lifecycle.ViewModel;

import com.sample.news.di.app.ViewModelKey;
import com.sample.news.ui.detail.NewsDetailViewModel;
import com.sample.news.ui.list.NewsListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * ViewModels in news screen listed for injection.
 */
@Module
public abstract class NewsModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel.class)
    public abstract ViewModel bindNewsListViewModel(NewsListViewModel viewModel);
}

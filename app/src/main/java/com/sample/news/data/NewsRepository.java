package com.sample.news.data;

import androidx.lifecycle.LiveData;

import com.sample.news.data.models.NewsResponse;
import com.sample.news.ui.Resource;

import javax.inject.Inject;

/**
 * Respository class works as an abstraction between ViewModel and API.
 */
public class NewsRepository {
    private static final String TAG = "NewsRepository";

    private NewsDataSource mNewsDataSource;

    @Inject
    public NewsRepository(NewsDataSource newsDataSource) {
        mNewsDataSource = newsDataSource;
    }

    public LiveData<Resource<NewsResponse>> fetchNews() {
        return mNewsDataSource.fetchNews();
    }
}

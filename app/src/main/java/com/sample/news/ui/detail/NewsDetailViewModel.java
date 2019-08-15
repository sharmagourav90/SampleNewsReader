package com.sample.news.ui.detail;

import androidx.lifecycle.ViewModel;

import com.sample.news.data.models.NewsEntity;

/**
 * View model for detail screen.
 */
public class NewsDetailViewModel extends ViewModel {
    private NewsEntity mNewsItem ;

    public void setNewsItem(NewsEntity newsItem) {
        this.mNewsItem = newsItem;
    }

    public NewsEntity getNewsItem() {
        return mNewsItem;
    }
}

package com.sample.news.ui;
import androidx.lifecycle.ViewModel;

import com.sample.news.data.models.NewsEntity;

/**
 * ViewModel class for MainActivity screen. Contains the news detail for
 * the item clicked to show in detail fragment.
 */
public class NewsActivityViewModel extends ViewModel {
    private NewsEntity mNewsDetail;
    private boolean isTablet;

    public NewsEntity getNewsDetail() {
        return mNewsDetail;
    }

    public void setSelectedNewsItem(NewsEntity newsDetail) {
        mNewsDetail = newsDetail;
    }

    public boolean isTablet() {
        return isTablet;
    }

    public void setTablet(boolean tablet) {
        isTablet = tablet;
    }
}

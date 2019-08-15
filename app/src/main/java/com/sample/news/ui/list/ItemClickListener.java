package com.sample.news.ui.list;

import com.sample.news.data.models.NewsEntity;

/**
 * Interface for item click callback from news list fragment
 */
public interface ItemClickListener {
    void onItemClick(NewsEntity newsItem);
}

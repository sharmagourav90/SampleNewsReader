package com.sample.news.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.sample.news.R;
import com.sample.news.ui.detail.NewsDetailFragment;
import com.sample.news.ui.list.NewsListFragment;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Main activity containing news list and news details fragment
 * For tablets, both the fragment will be visible at a time.
 */
public class MainActivity extends DaggerAppCompatActivity {
    private final String NEWS_LIST_TAG = "NEWS_LIST_FRAGMENT";
    private final String NEWS_DETAIL_TAG = "NEWS_DETAIL_FRAGMENT";

    private NewsActivityViewModel mNewsActivityViewModel;
    private TextView mDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mDetailTextView = findViewById(R.id.detail_text_view);

        mNewsActivityViewModel = ViewModelProviders.of(this).get(NewsActivityViewModel.class);

        mNewsActivityViewModel.setTablet(isTablet());

        if (mNewsActivityViewModel.isTablet()) {
            if (mNewsActivityViewModel.getNewsDetail() == null)
                mDetailTextView.setVisibility(View.VISIBLE);
            else
                mDetailTextView.setVisibility(View.GONE);
        }

        NewsListFragment fragment = (NewsListFragment) getSupportFragmentManager().findFragmentByTag(NEWS_LIST_TAG);

        if (fragment == null) {
            fragment = new NewsListFragment();
            if (isTablet())
                getSupportFragmentManager().beginTransaction().add(R.id.list_container, fragment, NEWS_LIST_TAG).commit();
            else
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, NEWS_LIST_TAG).commit();
        }
    }

    /**
     * Display the detail fragment based on the item clicked from news list
     */
    public void showDetailFragment() {
        NewsDetailFragment fragment = (NewsDetailFragment) getSupportFragmentManager().findFragmentByTag(NEWS_DETAIL_TAG);

        if (fragment == null) {
            fragment = NewsDetailFragment.newInstance(mNewsActivityViewModel.getNewsDetail());
            if (mNewsActivityViewModel.isTablet()) {
                mDetailTextView.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().add(R.id.detail_container, fragment, NEWS_DETAIL_TAG).commit();
            } else
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, NEWS_DETAIL_TAG).
                        addToBackStack(null).commit();
        } else {
            fragment.setData(mNewsActivityViewModel.getNewsDetail());
        }
    }

    /**
     * Check the device if it's a tablet
     *
     * @return true if tablet
     */
    public boolean isTablet() {
        return (getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}

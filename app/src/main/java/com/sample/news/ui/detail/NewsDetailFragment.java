package com.sample.news.ui.detail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.news.R;
import com.sample.news.data.models.MediaEntity;
import com.sample.news.data.models.NewsEntity;
import com.sample.news.databinding.FragmentNewsDetailBinding;

import java.util.List;

/**
 * Fragment that displays the details of news item.
 */
public class NewsDetailFragment extends Fragment {

    private NewsDetailViewModel mViewModel;
    private FragmentNewsDetailBinding binding;

    /**
     * Better way of creating fragments when data needs to be passed
     * before fragment creation.
     * @param newsItem news item details
     * @return fragment with news details set.
     */
    public static NewsDetailFragment newInstance(NewsEntity newsItem) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("newsItem", newsItem);
        newsDetailFragment.setArguments(args);
        return newsDetailFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(NewsDetailViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_detail, container, false);
        binding.setLifecycleOwner(this);

        NewsEntity newsItem = (NewsEntity) getArguments().getParcelable("newsItem");
        mViewModel.setNewsItem(newsItem);
        binding.setNewsItem(newsItem);

        setUrl(newsItem);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.fullStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFullStory();
            }
        });
    }

    /**
     * Full story web link will be opened.
     */
    public void onFullStory() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(mViewModel.getNewsItem().getUrl()));
        startActivity(intent);
    }

    /**
     * Method to set the url in the binding.
     * @param newsItem
     */
    private void setUrl(NewsEntity newsItem) {
        String url = null;
        List<MediaEntity> multiMediaList = newsItem.getMultimedia();
        if(multiMediaList != null) {
            url = multiMediaList.get(0).getUrl();
        }
        binding.setUrl(url);
    }

    /**
     * Setting data in binding and viewmodel
     * @param newsItem
     */
    public void setData(NewsEntity newsItem) {
        binding.setNewsItem(newsItem);
        mViewModel.setNewsItem(newsItem);
        setUrl(newsItem);
    }

}

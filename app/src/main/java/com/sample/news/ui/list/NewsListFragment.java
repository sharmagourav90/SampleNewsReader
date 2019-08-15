package com.sample.news.ui.list;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sample.news.R;
import com.sample.news.data.models.NewsEntity;
import com.sample.news.data.models.NewsResponse;
import com.sample.news.ui.MainActivity;
import com.sample.news.ui.NewsActivityViewModel;
import com.sample.news.ui.Resource;
import com.sample.news.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Fragment to display list of news items on news screen.
 */
public class NewsListFragment extends DaggerFragment implements ItemClickListener {
    private static final String TAG = "NewsListFragment";

    private NewsListViewModel mViewModel;

    private NewsActivityViewModel mNewsActivityViewModel;

    private RecyclerView mNewsRecyclerView;

    NewsRecyclerAdapter adapter;

    private ProgressBar mProgressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mNewsRecyclerView = view.findViewById(R.id.news_recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mViewModel = ViewModelProviders.of(this, providerFactory).get(NewsListViewModel.class);
        mNewsActivityViewModel = ViewModelProviders.of(getActivity()).get(NewsActivityViewModel.class);
        initRecyclerView();
        subscribeObervers();
    }

    private void subscribeObervers() {
        mViewModel.getNewsLiveData().observe(getViewLifecycleOwner(), new Observer<Resource<NewsResponse>>() {
            @Override
            public void onChanged(Resource<NewsResponse> newsResponseResource) {
                if (newsResponseResource != null) {
                    switch (newsResponseResource.status) {

                        case LOADING: {
                            //Toast.makeText(getActivity(), getResources().getString(R.string.loading_data), Toast.LENGTH_LONG).show();
                            mProgressBar.setVisibility(View.VISIBLE);
                            break;
                        }

                        case SUCCESS: {
                            adapter.setNewsItems(newsResponseResource.data.getResults());
                            mProgressBar.setVisibility(View.GONE);
                            break;
                        }

                        case ERROR: {
                            mProgressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                            break;
                        }

                        case NO_CONNECTION:
                            mProgressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), getResources().getString(R.string.no_connection), Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NewsRecyclerAdapter(this);
        mNewsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(NewsEntity newsItem) {
        mNewsActivityViewModel.setSelectedNewsItem(newsItem);
        ((MainActivity) getActivity()).showDetailFragment();
    }
}

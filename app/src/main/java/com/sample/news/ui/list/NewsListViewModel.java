package com.sample.news.ui.list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.sample.news.data.NewsRepository;
import com.sample.news.data.models.NewsResponse;
import com.sample.news.ui.Resource;

import javax.inject.Inject;

/**
 * ViewModel for the NewstList fragment
 */
public class NewsListViewModel extends ViewModel {
    private static final String TAG = "NewsListViewModel";
    private MediatorLiveData<Resource<NewsResponse>> mNewsLiveData;
    private NewsRepository mRepository;

    @Inject
    public NewsListViewModel(NewsRepository repository) {
        mRepository = repository;
        fetchNews();
    }

    public LiveData<Resource<NewsResponse>> fetchNews() {
        if(mNewsLiveData == null) {
            mNewsLiveData = new MediatorLiveData<>();
            mNewsLiveData.setValue(Resource.loading((NewsResponse) null));

            final LiveData<Resource<NewsResponse>> newsResponse = mRepository.fetchNews();
            mNewsLiveData.addSource(newsResponse, new Observer<Resource<NewsResponse>>() {
                @Override
                public void onChanged(Resource<NewsResponse> newsResponseResource) {
                    mNewsLiveData.setValue(newsResponseResource);
                    mNewsLiveData.removeSource(newsResponse);
                }
            });
        }
        return mNewsLiveData;
    }

    public LiveData<Resource<NewsResponse>> getNewsLiveData() {
        return mNewsLiveData;
    }
}

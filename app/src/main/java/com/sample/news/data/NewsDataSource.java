package com.sample.news.data;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sample.news.data.models.NewsResponse;
import com.sample.news.data.network.NewsApi;
import com.sample.news.ui.Resource;
import com.sample.news.util.Constants;
import com.sample.news.util.NoConnectivityException;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Data Source class for News List screen. Works as an
 * abstraction to fetch data from server.
 */
public class NewsDataSource {
    private static final String TAG = "NewsDataSource";
    private NewsApi mNewsApi;

    @Inject
    public NewsDataSource(NewsApi newsApi) {
        mNewsApi = newsApi;
    }

    /**
     * Business logic for webservice call to fetch the data.
     * @return Observable API response wrapped in with error / success message.
     */
    public LiveData<Resource<NewsResponse>> fetchNews() {
        final MutableLiveData<Resource<NewsResponse>> newsResponseLiveData = new MutableLiveData<>();
        mNewsApi.getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                newsResponseLiveData.setValue(Resource.success(response.body()));
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable throwable) {
                NewsResponse newsResponse = new NewsResponse();
                if (throwable instanceof NoConnectivityException) {
                    newsResponseLiveData.setValue(Resource.noConnection("", (NewsResponse)null));
                } else {
                    newsResponseLiveData.setValue(Resource.error("", (NewsResponse)null));
                }
            }
        });

        return newsResponseLiveData;
    }
}

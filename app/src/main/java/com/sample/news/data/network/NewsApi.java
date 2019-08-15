package com.sample.news.data.network;

import com.sample.news.data.models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * API for fetching news list
 */
public interface NewsApi {
    @GET(".")
    Call<NewsResponse> getNews();
}

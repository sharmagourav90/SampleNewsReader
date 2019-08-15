package com.sample.news.di.news.module;

import android.app.Application;
import android.util.Log;

import com.sample.news.data.NewsDataSource;
import com.sample.news.data.NewsRepository;
import com.sample.news.data.models.NewsResponse;
import com.sample.news.data.network.NetworkConnectionInterceptor;
import com.sample.news.data.network.NewsApi;
import com.sample.news.data.network.NewsResponseParser;
import com.sample.news.di.news.scope.NewsScope;
import com.sample.news.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class responsible for providing dependencies in News screen scope.
 * Repository, API and their dependencies.
 */
@Module
public class NewsModule {
    @NewsScope
    @Provides
    static NewsRepository provideRepository(NewsDataSource newsDataSource) {
        return new NewsRepository(newsDataSource);
    }

    @NewsScope
    @Provides
    static NewsDataSource provideDataSource(NewsApi newsApi) {
        return new NewsDataSource(newsApi);
    }

    @NewsScope
    @Provides
    static NewsApi provideNewsApi(Retrofit retrofit) {
        return retrofit.create(NewsApi.class);
    }

    @NewsScope
    @Provides
    static Retrofit provideRetrofitInstance(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @NewsScope
    @Provides
    OkHttpClient getClient(Application application) {
        return new OkHttpClient.Builder()
                .addInterceptor(new NetworkConnectionInterceptor(application))
                .build();
    }

    @NewsScope
    @Provides
    static Gson provideGsonInstance(NewsResponseParser newsResponseParser) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(NewsResponse.class, newsResponseParser);
        return gsonBuilder.create();
    }

    @NewsScope
    @Provides
    static NewsResponseParser provideNewsResponseParser() {
        return new NewsResponseParser();
    }

}

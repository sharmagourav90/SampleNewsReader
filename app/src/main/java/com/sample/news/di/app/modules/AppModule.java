package com.sample.news.di.app.modules;

import android.app.Application;
import android.util.Log;

import com.sample.news.data.NewsDataSource;
import com.sample.news.data.NewsRepository;
import com.sample.news.data.models.NewsResponse;
import com.sample.news.data.network.NetworkConnectionInterceptor;
import com.sample.news.data.network.NewsApi;
import com.sample.news.data.network.NewsResponseParser;
import com.sample.news.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;

/**
 * Class responsible for providing dependencies at app level.
 */
@Module
public class AppModule {

}
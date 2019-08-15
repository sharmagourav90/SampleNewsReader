package com.sample.news.di.app.component;

import android.app.Application;

import com.sample.news.SampleNewsApp;
import com.sample.news.di.app.modules.ActivityBuildersModule;
import com.sample.news.di.app.modules.AppModule;
import com.sample.news.di.app.modules.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * AppComponent for application wide scope. (Singleton scope)
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class,
        ViewModelFactoryModule.class})
public interface AppComponent extends AndroidInjector<SampleNewsApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

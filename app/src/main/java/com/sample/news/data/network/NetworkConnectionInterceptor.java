package com.sample.news.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sample.news.util.NoConnectivityException;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Class responsible for intercepting network unavailability event.
 * Throw NoConnectivityException in case of no internet connection
 */
public class NetworkConnectionInterceptor implements Interceptor {
    private Context mContext;

    @Inject
    public NetworkConnectionInterceptor(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * Check if there is network connection on device
     * @return true if connection
     */
    private boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Intercept the request, if no connection throw exception
     * @param chain
     * @return response
     * @throws IOException NoNetworkConnection
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        if(!isOnline())
            throw new NoConnectivityException();
        return chain.proceed(chain.request());
    }
}

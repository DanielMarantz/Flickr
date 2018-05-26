package com.flickr.assessment.flickrassessment.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RestClient instantiates the service connection.
 */
@Singleton
public class RestClient {

    public static final String TAG = RestClient.class.getSimpleName();

    private Gson gson;
    private OkHttpClient httpClient;
    private Retrofit retrofit;
    private RestService service;

    public RestService getService() {
        configureGson();
        configureOkHttp();
        configureRetrofit();
        configureService();
        return service;
    }

    private void configureGson() {
        gson = new GsonBuilder().create();
    }

    private void configureRetrofit() {
        Log.d(TAG, "Retrofit Base URL: " + RestConfig.API_URL);

        retrofit = new Retrofit.Builder()
                .baseUrl(RestConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
    }

    public OkHttpClient configureOkHttp() {

        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {

                HttpUrl url = chain.request().url().newBuilder()
                        .addEncodedQueryParameter("format", "json")
                        .addEncodedQueryParameter("nojsoncallback", "1")
                        .build();

                Request request = chain.request().newBuilder()
                        // .addHeader("Authorization", "Bearer token")
                        .url(url)
                        .build();

                return chain.proceed(request);
            }
        };

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor).addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private void configureService() {
        service = retrofit.create(RestService.class);
    }
}

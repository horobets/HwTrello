package com.trello.api.interceptors;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class TrelloAuthInterceptor implements Interceptor {


    private static final String KEY = "320a7b34bddbc5db9d1fa9b950a35204";
    private static final String TOKEN = "cefc3884e73d2fbae24d0c7aa39fd0b1e48e3209b8c73e9bfc930588a05d147c";


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", KEY)
                .addQueryParameter("token", TOKEN)
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }


}
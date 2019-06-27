package com.trello.api.interceptors;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashSet;

//ref: https://gist.github.com/tsuharesu/cbfd8f02d46498b01f1b

public class AddCookiesInterceptor implements Interceptor {

    private HashSet<String> cookies;

    public AddCookiesInterceptor(HashSet<String> cookies) {
        this.cookies = cookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        for (String cookie : cookies) {
            builder.addHeader("Cookie", cookie);
            System.out.println("OkHttp " + "Adding Header: " + cookie);
        }

        return chain.proceed(builder.build());
    }
}
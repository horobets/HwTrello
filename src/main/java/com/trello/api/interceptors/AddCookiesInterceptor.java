package com.trello.api.interceptors;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashSet;

//ref: https://gist.github.com/tsuharesu/cbfd8f02d46498b01f1b

public class AddCookiesInterceptor implements Interceptor {

    private HashSet<String> preferences;

    public AddCookiesInterceptor(HashSet<String> preferences) {
        this.preferences = preferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            //Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            System.out.println("OkHttp" + "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }

        return chain.proceed(builder.build());
    }
}
package com.trello.api.interceptors;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashSet;

public class ReceivedCookiesInterceptor implements Interceptor {

    private HashSet<String> cookies;

    public ReceivedCookiesInterceptor(HashSet<String> cookies) {
        this.cookies = cookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            this.cookies.addAll(cookies);
        }

        return originalResponse;
    }
}
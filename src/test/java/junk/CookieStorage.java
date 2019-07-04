package junk;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by horobets on Jul 03, 2019
 */
public class CookieStorage implements CookieJar {

    public List<Cookie> cookies = new ArrayList<>();

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        cookies.addAll(list);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return cookies;
    }

}
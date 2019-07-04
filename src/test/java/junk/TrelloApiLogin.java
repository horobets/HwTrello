package junk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trello.ui.core.BrowserFactory;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;


/**
 * Created by horobets on Jul 02, 2019
 */
public class TrelloApiLogin extends BrowserFactory {


    CookieStorage cookieStorage = new CookieStorage();


    @Test
    public void tryIt() throws IOException, InterruptedException {
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStorage).build();
        client.newCall(new Request.Builder().url("https://trello.com").build()).execute().body().string();
        FormBody formData = new FormBody.Builder()
                .add("method", "password")
                .add("factors[user]", "loliktestintegration@gmail.com")
                .add("factors[password]", "qwe1rty2")
                .build();
        Request request = new Request.Builder().url("https://trello.com/1/authentication").post(formData).build();
        String response = client.newCall(request).execute().body().string();
        Map<String, String> map = new Gson().fromJson(response, new TypeToken<Map<String, String>>() {
        }.getType());
        String code = map.get("code");
        System.out.println("RESPONSE: " + code);

        System.out.println(cookieStorage.cookies);

        FormBody formData2 = new FormBody.Builder()
                .add("authentication", code)
                .add("dsc", cookieStorage.cookies.stream().filter(cookie -> cookie.name().equals("dsc")).findFirst().get().value())
                .build();
        Request requestSession = new Request.Builder().url("https://trello.com/1/authorization/session").post(formData2).build();
        response = client.newCall(requestSession).execute().body().string();
        System.out.println(response);
        get("https://trello.com");

        for (Cookie cookie : cookieStorage.cookies) {
            driver().manage().addCookie(new org.openqa.selenium.Cookie(cookie.name(), cookie.value()));
        }

        driver().navigate().refresh();
        Thread.sleep(10000);

    }


}
package exam.trello;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.AuthResponseData;
import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.BoardsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.testng.Assert;

import java.io.IOException;

import static com.trello.ui.core.Constants.PASSWORD;
import static com.trello.ui.core.Constants.USERNAME;

/**
 * Created by horobets on Jun 20, 2019
 */

public class LoginByApi extends BrowserFactory {
    TrelloRestClient client = new TrelloRestClient(cookies);

    public void loginByApi() throws IOException {

        //API+Cookies Auth
        client.authService.homepage().execute();
        String dsc = getCookieValue("dsc");
        AuthResponseData authResponseData = client.authService.authentication("password", USERNAME, PASSWORD).execute().body();
        client.authService.session(authResponseData.code, dsc).execute().body();

        driver().navigate().to("https://trello.com");

        String token = getCookieValue("token");
        Cookie authCookie = new Cookie("token", token);
        driver().manage().addCookie(authCookie);

        driver().navigate().refresh();

        Assert.assertTrue(new BoardsPage().isOpened(), "Not a Boards page. Authentication failed?");
    }

    @Step
    private String getCookieValue(String cookieName) {
        String cookieValue = "";
        for (String s : cookies) {
            if (s.startsWith(String.format("%s=", cookieName))) {
                cookieValue = s.substring(s.indexOf("=") + 1, s.indexOf(";"));
            }
        }
        return cookieValue;
    }
}
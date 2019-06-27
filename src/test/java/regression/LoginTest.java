package regression;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.AuthResponseData;
import com.trello.ui.core.BrowserFactory;
import com.trello.ui.core.credentialsstorage.Credentials;
import com.trello.ui.core.credentialsstorage.CredentialsStorage;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.LoginPage;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by horobets on Jun 20, 2019
 */
public class LoginTest extends BrowserFactory {

    protected final String credentialsStorageFilePath = "c:\\credentials\\trellocredentials.txt";

    TrelloRestClient client = new TrelloRestClient(cookies);

    LoginPage loginPage = new LoginPage();
    BoardsPage boardsPage = new BoardsPage();

    @Parameters({"username", "password"})
    @Test(description = "Test trello login screen")
    public void login(@Optional("") String username,
                      @Optional("") String password) throws IOException {

        //use credentialsstorage if no credentials provided
        if (username.isEmpty() || password.isEmpty()) {
            Credentials trelloCredentials = (new CredentialsStorage(credentialsStorageFilePath)).getLastCredentials();
            username = trelloCredentials.getUsername();
            password = trelloCredentials.getPassword();
        }

        /*
        loginPage.open();
        loginPage.login(username, password);
        */

        //API+Cookies Auth

        client.authService.homepage().execute();

        String dsc = getCookieValue("dsc");
        AuthResponseData authResponseData = client.authService.authentication("password", username, password).execute().body();

        client.authService.session(authResponseData.code, dsc).execute().body();

        driver().navigate().to("https://trello.com");

        String token = getCookieValue("token");
        Cookie authCookie = new Cookie("token", token);
        driver().manage().addCookie(authCookie);

        driver().navigate().refresh();

        boardsPage.openBoard("jacksparrowtitle");
    }

    private String getCookieValue(String cookieName) {
        String cookieValue = "";
        for (String s : cookies) {
            if (s.startsWith(String.format("%s=", cookieName))) {
                //dsc=6b5759f43105d69a69cd98f4b487e760f9c298a3e0c314e6c25b488f1e19ac7e; Path=/; Expires=Sat, 29 Jun 2019 20:45:32 GMT; Secure
                cookieValue = s.substring(s.indexOf("=") + 1, s.indexOf(";"));
            }
        }
        return cookieValue;
    }
}
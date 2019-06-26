package regression;

import com.trello.ui.core.BrowserFactory;
import com.trello.ui.core.credentialsstorage.Credentials;
import com.trello.ui.core.credentialsstorage.CredentialsStorage;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.LoginPage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by horobets on Jun 20, 2019
 */
public class LoginTest extends BrowserFactory {


    protected final String credentialsStorageFilePath = "c:\\credentials\\trellocredentials.txt";

    LoginPage loginPage = new LoginPage();
    BoardsPage boardsPage = new BoardsPage();

    @Parameters({"username", "password"})
    @Test(description = "Test trello login screen", priority = 1/*, dataProvider = "TestDataProvider"-*/)
    public void login(@Optional("") String username,
                      @Optional("") String password) {

        //use credentialsstorage if no credentials provided
        if (username.isEmpty() || password.isEmpty()) {
            Credentials trelloCredentials = (new CredentialsStorage(credentialsStorageFilePath)).getLastCredentials();
            username = trelloCredentials.getUsername();
            password = trelloCredentials.getPassword();
        }


        loginPage.open();
        loginPage.login(username, password);
        boardsPage.openBoard("jacksparrowtitle");
    }

}
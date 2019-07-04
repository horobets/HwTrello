package regression.legacy;

import com.trello.ui.core.credentialsstorage.Credentials;
import com.trello.ui.core.credentialsstorage.CredentialsStorage;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.LoggedOutPage;
import com.trello.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class LoginTests extends TrelloBaseTest {


    protected final String credentialsStorageFilePath = "c:\\credentials\\trellocredentials.txt";

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

        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(username, password);
        Assert.assertTrue(new BoardsPage().isOpened(), "Board page was not found");

    }


    @Test(description = "Test trello logout screen", dependsOnMethods = "login", priority = 9)
    public void logout() {

        BoardsPage boardsPage = new BoardsPage();

        boardsPage.logOut();

        Assert.assertTrue(new LoggedOutPage().isOpened(), "Logged Out page was not found");


    }
}

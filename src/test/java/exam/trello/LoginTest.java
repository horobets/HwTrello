package exam.trello;

import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import regression.legacy.TrelloBaseTest;

import static com.trello.ui.core.Constants.PASSWORD;
import static com.trello.ui.core.Constants.USERNAME;


public class LoginTest extends TrelloBaseTest {

    @Parameters({"username", "password"})
    @Test(description = "Test trello login screen", priority = 2)
    public void login(@Optional("") String username,
                      @Optional("") String password) {

        if (username.isEmpty() && password.isEmpty()) {
            username = USERNAME;
            password = PASSWORD;
        }

        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(username, password);

        Assert.assertTrue(new BoardsPage().isOpened(), "Boards page was not found. Auth failed?");
    }

    @Test(description = "Login with invalid credentials test", priority = 1)
    public void loginInvalid() {

        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("InvalidUsername", "InvalidPassword");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Invalid Password error message is not displayed.");
    }
}
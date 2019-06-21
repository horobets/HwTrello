package regression;

import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.LoginPage;
import org.testng.annotations.Test;

/**
 * Created by horobets on Jun 20, 2019
 */
public class LoginTest extends BrowserFactory {

    LoginPage loginPage = new LoginPage();
    BoardsPage boardsPage = new BoardsPage();

    @Test
    public void login() throws InterruptedException {
        loginPage.open();
        loginPage.login("loliktestintegration@gmail.com", "iLoveBieber");
        boardsPage.openBoard("jacksparrowtitle");
    }

}
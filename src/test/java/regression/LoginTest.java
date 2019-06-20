package regression;

import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.LoginPage;

/**
 * Created by horobets on Jun 20, 2019
 */
public class LoginTest extends BrowserFactory {
    LoginPage loginPage = new LoginPage();
    BoardsPage boardsPage = new BoardsPage();

    public void login() throws InterruptedException {
        loginPage.open();
        loginPage.login("ddd@fdsf.om", "sss");
        boardsPage.op
        Thread.sleep(10000);
    }
}

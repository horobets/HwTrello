package exam.trello;

import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.LoggedOutPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import regression.legacy.TrelloBaseTest;

import java.io.IOException;


public class LogoutTest extends TrelloBaseTest {

    @BeforeClass
    public void prepareData() throws IOException {
        new LoginByApi().loginByApi();
    }

    @AfterClass
    public void clearData() throws IOException {
    }

    @Test(description = "Test trello logout")
    public void logout() {

        BoardsPage boardsPage = new BoardsPage();

        boardsPage.logOut();

        Assert.assertTrue(new LoggedOutPage().isOpened(), "Logged Out page was not found");
    }
}

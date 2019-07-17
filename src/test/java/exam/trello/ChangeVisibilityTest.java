package exam.trello;

import com.trello.ui.pages.BoardPage;
import com.trello.ui.pages.BoardVisibility;
import com.trello.ui.pages.BoardsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import regression.legacy.TrelloBaseTest;

import java.io.IOException;

public class ChangeVisibilityTest extends TrelloBaseTest {

    @BeforeMethod
    public void signIn() throws IOException {
        new LoginByApi().loginByApi();
    }

    @Parameters({"boardName"})
    @Test(description = "Test trello make board public", priority = 1)
    public void makeBoardPublic(@Optional("James Board") String boardName) {

        BoardsPage boardsPage = new BoardsPage();

        BoardPage boardPage = boardsPage.openBoardByName(boardName);

        boardPage.changeVisibility(BoardVisibility.PUBLIC);

        String publicBoardUrl = driver().getCurrentUrl();

        boardPage.openBoardsPage().isOpened();

        // try to open board form not logged in browser window:
        new BoardsPage().logOut();
        driver().navigate().to(publicBoardUrl);

        Assert.assertTrue(new BoardPage().isOpened(), "Board Page not opened in non-logged in browser window. Board is not public");
    }


    @Parameters({"boardName"})
    @Test(description = "Test trello make board private", priority = 2)
    public void makeBoardPrivate(@Optional("James Board") String boardName) {

        BoardsPage boardsPage = new BoardsPage();

        BoardPage boardPage = boardsPage.openBoardByName(boardName);

        boardPage.changeVisibility(BoardVisibility.PRIVATE);

        String publicBoardUrl = driver().getCurrentUrl();

        boardPage.openBoardsPage().isOpened();

        // try to open board form not logged in browser window:
        boardsPage.logOut();
        driver().navigate().to(publicBoardUrl);

        Assert.assertFalse(new BoardPage().isOpened(), "Board Page opened in non-logged in browser window. Board is public");
    }
}

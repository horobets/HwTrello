package regression.legacy;

import com.trello.ui.pages.BoardPage;
import com.trello.ui.pages.BoardsPage;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateDeleteBoardTest extends TrelloBaseTest {

    @Parameters({"boardname"})
    @Test(description = "Test trello create board")
    public void createBoard(@Optional("James Board") String boardname) {

        BoardsPage boardsPage = new BoardsPage();
        boardsPage.isOpened();
        boardsPage.createBoard(boardname);

        boardsPage.openBoardsPage();
        boardsPage.isOpened();
        Assert.assertTrue(boardsPage.isBoardListed(boardname));
    }


    @Parameters({"boardname"})
    @Test(description = "Test trello delete board", priority = 9)
    public void deleteBoard(@Optional("James Board") String boardname) {

        BoardsPage boardsPage = new BoardsPage();
        boardsPage.openBoardsPage();
        boardsPage.isOpened();

        BoardPage boardPage = boardsPage.openBoardByName(boardname);
        boardPage.deleteBoard();

        boardPage.openBoardsPage().isOpened();

        Assert.assertFalse(boardsPage.isBoardListed(boardname));
    }

}

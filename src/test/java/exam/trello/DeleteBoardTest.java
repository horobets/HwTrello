package exam.trello;

import com.trello.ui.pages.BoardPage;
import com.trello.ui.pages.BoardsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import regression.legacy.TrelloBaseTest;

import java.io.IOException;

public class DeleteBoardTest extends TrelloBaseTest {

    @BeforeClass
    public void prepareData() throws IOException {
        new LoginByApi().loginByApi();
    }

    @Parameters({"boardName"})
    @Test(description = "Test trello delete board")
    public void deleteBoard(@Optional("James Board") String boardName) {

        BoardsPage boardsPage = new BoardsPage();

        if (!boardsPage.isOpened())
            boardsPage.openBoardsPage();

        BoardPage boardPage = boardsPage.openBoardByName(boardName);
        boardPage.deleteBoard();

        boardPage.openBoardsPage();

        Assert.assertFalse(boardsPage.isBoardListed(boardName));
    }

}

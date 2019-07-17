package exam.trello;

import com.trello.ui.pages.BoardsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import regression.legacy.TrelloBaseTest;

import java.io.IOException;

public class CreateBoardTest extends TrelloBaseTest {

    @BeforeClass
    public void prepareData() throws IOException {
        new LoginByApi().loginByApi();
    }

    @Parameters({"boardName"})
    @Test(description = "Test trello create board")
    public void createBoard(@Optional("James Board") String boardName) {

        BoardsPage boardsPage = new BoardsPage();
        boardsPage.isOpened();
        boardsPage.createBoard(boardName);

        boardsPage.openBoardsPage();
        boardsPage.isOpened();
        Assert.assertTrue(boardsPage.isBoardListed(boardName));
    }


}

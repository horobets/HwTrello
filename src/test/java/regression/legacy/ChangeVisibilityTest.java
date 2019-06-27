package regression.legacy;

import com.trello.ui.pages.BoardPage;
import com.trello.ui.pages.BoardVisibility;
import com.trello.ui.pages.BoardsPage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ChangeVisibilityTest extends TrelloBaseTest {

    @Parameters({"boardname"})
    @Test(description = "Test trello make board public")
    public void makeBoardPublic(@Optional("James Board") String boardname) {

        BoardsPage boardsPage = new BoardsPage();

        BoardPage boardPage = boardsPage.openBoardByName(boardname);

        boardPage.changeVisibility(BoardVisibility.PUBLIC);

        boardPage.openBoardsPage().isOpened();
    }


    @Parameters({"boardname"})
    @Test(description = "Test trello make board private")
    public void makeBoardPrivate(@Optional("James Board") String boardname) {

        BoardsPage boardsPage = new BoardsPage();

        BoardPage boardPage = boardsPage.openBoardByName(boardname);

        boardPage.changeVisibility(BoardVisibility.PRIVATE);

        boardPage.openBoardsPage().isOpened();
    }
}

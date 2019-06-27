package regression.legacy;

import com.trello.ui.pages.BoardPage;
import com.trello.ui.pages.BoardsPage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddToFavoritesTest extends TrelloBaseTest {

    @Parameters({"boardname"})
    @Test(description = "Test trello add board to favorites")
    public void addBoardToFavorites(@Optional("James Board") String boardname) {

        BoardsPage boardsPage = new BoardsPage();
        //boardsPage.openBoardsPage();
        //boardsPage.isOpened();

        BoardPage boardPage = boardsPage.openBoardByName(boardname);

        boardPage.clickStar();

        boardPage.openBoardsPage().isOpened();
    }


    @Parameters({"boardname"})
    @Test(description = "Test trello remove board from favorites")
    public void removeBoardFromFavorites(@Optional("James Board") String boardname) {

        BoardsPage boardsPage = new BoardsPage();
        //boardsPage.openBoardsPage();
        //boardsPage.isOpened();

        BoardPage boardPage = boardsPage.openBoardByName(boardname);

        boardPage.clickStar();

        boardPage.openBoardsPage().isOpened();
    }
}

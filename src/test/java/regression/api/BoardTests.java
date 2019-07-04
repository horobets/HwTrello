package regression.api;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Board;
import junk.TryLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by horobets on Jul 04, 2019
 */
public class BoardTests {
    TrelloRestClient client = new TrelloRestClient();
    private Logger logger = LoggerFactory.getLogger(TryLogger.class);
    private String testBoardId = "";
    private String testBoardName = String.format("Test Board - %s", new Date().getTime());


    @Test(description = "Test create trello board", priority = 1)
    public void createBoardTest() throws IOException, InterruptedException {
        Board createdBoard = client.boardsService.createBoard(testBoardName).execute().body();
        testBoardId = createdBoard.id;
        Assert.assertNotNull(createdBoard, "Created board was not found");
        Assert.assertEquals(createdBoard.name, testBoardName, "Invalid board was found");
    }

    @Test(description = "Test get trello board", priority = 2)
    public void getBoardTest() throws IOException {

        Board board = client.boardsService.getBoard(this.testBoardId).execute().body();

        Assert.assertNotNull(board, "Board was not found");
        Assert.assertEquals(board.name, testBoardName, "Invalid board found");

        /*SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedTitle, actualTitle, "Title is incorrect");
        Assert.assertTrue(actualTitle.equals(expectedTitle), "sdfsdfsdfsdf");

        softAssert.assertAll();*/
    }

    @Test(description = "Test update trello board", priority = 3)
    public void updateBoardTest() throws IOException, InterruptedException {

        Board board = client.boardsService.getBoard(this.testBoardId).execute().body();

        String testDescription = String.format("Updated Board Description - %s", new Date().getTime());
        board.desc = testDescription;

        client.boardsService.updateBoard(this.testBoardId, board).execute().body();

        Board updatedBoard = client.boardsService.getBoard(this.testBoardId).execute().body();

        Assert.assertEquals(updatedBoard.desc, testDescription, "Invalid updated board");
    }


    @Test(description = "Test delete trello board", priority = 4)
    public void deleteBoardTest() throws IOException {

        client.boardsService.deleteBoard(testBoardId).execute();

        Board board = client.boardsService.getBoard(testBoardId).execute().body();

        Assert.assertNull(board, "Deleted board still exists");
    }
}

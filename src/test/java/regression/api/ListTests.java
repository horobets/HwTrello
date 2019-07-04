package regression.api;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Board;
import com.trello.api.models.TrelloList;
import junk.TryLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by horobets on Jul 04, 2019
 */
public class ListTests {
    TrelloRestClient client = new TrelloRestClient();
    private Logger logger = LoggerFactory.getLogger(TryLogger.class);
    private String testBoardId = "";
    private String testBoardName = String.format("Test Board - %s", new Date().getTime());
    private String testListId = "";
    private String testListName = String.format("Test List - %s", new Date().getTime());

    @BeforeClass
    public void setUp() throws IOException {
        Board createdBoard = client.boardsService.createBoard(testBoardName).execute().body();
        testBoardId = createdBoard.id;
        Assert.assertNotNull(createdBoard, "Created board not found");
        Assert.assertEquals(createdBoard.name, testBoardName, "Invalid board was found");
    }


    @Test(description = "Test create trello list", priority = 1)
    public void createListTest() throws IOException {
        TrelloList createdTrelloList = client.listsService.createList(testBoardId, testListName).execute().body();
        testListId = createdTrelloList.id;
        Assert.assertNotNull(createdTrelloList, "Created list was not found");
        Assert.assertEquals(createdTrelloList.name, testListName, "Invalid list was found");
    }

    @Test(description = "Test get trello list", priority = 2)
    public void getListTest() throws IOException {

        TrelloList list = client.listsService.getList(this.testListId).execute().body();

        Assert.assertNotNull(list, "List was not found");
        Assert.assertEquals(list.name, testListName, "Invalid list was found");
    }

    @Test(description = "Test get trello board lists", priority = 2)
    public void getBoardListsTest() throws IOException {

        List<TrelloList> boardLists = client.boardsService.getLists(this.testBoardId).execute().body();

        Assert.assertTrue(boardLists.stream().filter(o -> o.name.equals(testListName)).findFirst().isPresent(), "Test list was not found on the board");
    }

    @Test(description = "Test update trello list", priority = 3)
    public void updateListTest() throws IOException, InterruptedException {

        TrelloList list = client.listsService.getList(this.testListId).execute().body();

        String changedListName = String.format("Updated List Name - %s", new Date().getTime());
        list.name = changedListName;

        client.listsService.updateList(this.testListId, list).execute().body();

        TrelloList updatedList = client.listsService.getList(this.testListId).execute().body();

        Assert.assertEquals(updatedList.name, changedListName, "Invalid updated list");
    }

    @Test(description = "Test delete trello list", priority = 4)
    public void deleteListTest() throws IOException {

        client.listsService.archiveList(testListId, true).execute();

        List<TrelloList> boardLists = client.boardsService.getLists(this.testBoardId).execute().body();
        //Assert.assertFalse(boardLists.stream().filter(o -> o.name.equals(testListName)).findFirst().isPresent(), "Test list still exists on the board");
        //Assert.assertFalse(boardLists.stream().filter(o -> o.id.equals(testListId) && o.closed.equals(true)).findFirst().isPresent(), "Test list still exists on the board");

        Assert.assertTrue(client.listsService.getList(this.testListId).execute().body().closed, "Test list is still visible on the board");
    }

    @AfterClass
    public void tearDown() throws IOException {
        client.boardsService.deleteBoard(testBoardId).execute();
        Board board = client.boardsService.getBoard(testBoardId).execute().body();
        Assert.assertNull(board, "Deleted board still exists");
    }
}

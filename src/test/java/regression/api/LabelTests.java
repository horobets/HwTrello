package regression.api;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Board;
import com.trello.api.models.Card;
import com.trello.api.models.Label;
import com.trello.api.models.TrelloList;
import com.trello.api.services.LabelColor;
import junk.TryLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.Date;

/**
 * Created by horobets on Jul 04, 2019
 */
public class LabelTests {
    TrelloRestClient client = new TrelloRestClient();
    private Logger logger = LoggerFactory.getLogger(TryLogger.class);
    private String testBoardId = "";
    private String testBoardName = String.format("Test Board - %s", new Date().getTime());
    private String testListId = "";
    private String testListName = String.format("Test List - %s", new Date().getTime());
    private String testCardId = "";
    private String testCardName = String.format("Test Card - %s", new Date().getTime());

    private String testLabelId = "";
    private String testLabelName = String.format("Test Label - %s", new Date().getTime());
    private LabelColor testLabelColor = LabelColor.ORANGE;

    @BeforeClass
    public void setUp() throws IOException {
        Board createdBoard = client.boardsService.createBoard(testBoardName).execute().body();
        testBoardId = createdBoard.id;
        Assert.assertNotNull(createdBoard, "Created board not found");
        Assert.assertEquals(createdBoard.name, testBoardName, "Invalid board was found");

        TrelloList createdTrelloList = client.listsService.createList(testBoardId, testListName).execute().body();
        testListId = createdTrelloList.id;
        Assert.assertNotNull(createdTrelloList, "Created list was not found");
        Assert.assertEquals(createdTrelloList.name, testListName, "Invalid list was found");

        Card createdTrelloCard = client.cardsService.createCard(testListId, new Card(testCardName)).execute().body();
        testCardId = createdTrelloCard.id;
        Assert.assertNotNull(createdTrelloCard, "Created card was not found");
        Assert.assertEquals(createdTrelloCard.name, testCardName, "Invalid card was found");
    }

    @Test(description = "Test create trello label", priority = 1)
    public void createLabelTest() throws IOException {
        Label createdLabel = client.labelsService.createLabel(testLabelName, testLabelColor, testBoardId).execute().body();
        testLabelId = createdLabel.id;
        Assert.assertNotNull(createdLabel, "Created label was not found");
        Assert.assertEquals(createdLabel.name, testLabelName, "Invalid label was found");
    }


    @Test(description = "Test add label to Card", priority = 2)
    public void addLabelToCard() throws IOException {

        Response response = client.cardsService.addIdLabel(testCardId, testLabelId).execute();

        Card card = client.cardsService.getCard(this.testCardId).execute().body();

        Assert.assertEquals(card.labels[0].name, testLabelName, "Invalid label on card");
    }

    @Test(description = "Test remove label from card", priority = 3)
    public void removeLabelTest() throws IOException {

        client.cardsService.removeIdLabel(testCardId, testLabelId).execute();

        Card card = client.cardsService.getCard(this.testCardId).execute().body();

        Assert.assertFalse(card.labels.length > 0, "Card still contains labels");
    }

    @Test(description = "Test delete label", priority = 4)
    public void deleteLabelTest() throws IOException {

        client.labelsService.deleteLabel(testLabelId).execute();

        Label label = client.labelsService.getLabel(this.testLabelId).execute().body();

        Assert.assertNull(label, "Label still exists on board");
    }


    @AfterClass
    public void tearDown() throws IOException {
        client.boardsService.deleteBoard(testBoardId).execute();
        Board board = client.boardsService.getBoard(testBoardId).execute().body();
        Assert.assertNull(board, "Deleted board still exists");
    }
}

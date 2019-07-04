package regression.api;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Board;
import com.trello.api.models.Card;
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
public class CardTests {
    TrelloRestClient client = new TrelloRestClient();
    private Logger logger = LoggerFactory.getLogger(TryLogger.class);
    private String testBoardId = "";
    private String testBoardName = String.format("Test Board - %s", new Date().getTime());
    private String testListId = "";
    private String testListName = String.format("Test List - %s", new Date().getTime());
    private String testCardId = "";
    private String testCardName = String.format("Test Card - %s", new Date().getTime());

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
    }

    @Test(description = "Test create trello card", priority = 1)
    public void createCardTest() throws IOException {
        Card createdTrelloCard = client.cardsService.createCard(testListId, new Card(testCardName)).execute().body();
        testCardId = createdTrelloCard.id;
        Assert.assertNotNull(createdTrelloCard, "Created card was not found");
        Assert.assertEquals(createdTrelloCard.name, testCardName, "Invalid card was found");
    }

    @Test(description = "Test get trello card", priority = 2)
    public void getCardTest() throws IOException {

        Card card = client.cardsService.getCard(this.testCardId).execute().body();

        Assert.assertNotNull(card, "Card was not found");
        Assert.assertEquals(card.name, testCardName, "Invalid card was found");
    }


    @Test(description = "Test get trello list cards", priority = 2)
    public void getBoardListsTest() throws IOException {

        List<Card> listCards = client.listsService.getCards(testListId).execute().body();

        Assert.assertTrue(listCards.stream().filter(o -> o.name.equals(testCardName)).findFirst().isPresent(), "Test card was not found in the list");
    }

    @Test(description = "Test update trello card", priority = 3)
    public void updateCardTest() throws IOException, InterruptedException {

        Card card = client.cardsService.getCard(this.testCardId).execute().body();

        String changedCardName = String.format("Updated Card Name - %s", new Date().getTime());
        card.name = changedCardName;

        client.cardsService.updateCard(this.testCardId, card).execute().body();

        Card updatedCard = client.cardsService.getCard(this.testCardId).execute().body();

        Assert.assertEquals(updatedCard.name, changedCardName, "Invalid updated card");
    }

    @Test(description = "Test delete trello card", priority = 4)
    public void deleteCardTest() throws IOException {

        client.cardsService.deleteCard(testCardId).execute();

        List<Card> listCards = client.listsService.getCards(testListId).execute().body();

        Assert.assertNull(client.cardsService.getCard(this.testCardId).execute().body(), "Test list still exists on the board");
    }

    @AfterClass
    public void tearDown() throws IOException {
        client.boardsService.deleteBoard(testBoardId).execute();
        Board board = client.boardsService.getBoard(testBoardId).execute().body();
        Assert.assertNull(board, "Deleted board still exists");
    }
}

package tasks;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Board;
import com.trello.api.models.Card;
import com.trello.api.models.TrelloList;
import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.BoardPage;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.CardPopupPage;
import junk.TryLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by horobets on Jul 05, 2019
 */
public class CardActionsTests extends BrowserFactory {

    public TrelloRestClient client = new TrelloRestClient();
    public BoardsPage boardsPage = new BoardsPage();
    BoardPage boardPage = new BoardPage();
    public CardPopupPage cardPopupPage = new CardPopupPage();
    private Logger logger = LoggerFactory.getLogger(TryLogger.class);
    private String testBoardId = "";
    private String testBoardName = String.format("Test Board - %s", new Date().getTime());
    private String testListId = "";
    private String testListName = String.format("Test List - %s", new Date().getTime());
    private String testCardId = "";
    private String testCardName = String.format("Test Card - %s", new Date().getTime());

    @BeforeClass
    public void prepareData() throws IOException {
        Board createdBoard = client.boardsService.createBoard(testBoardName).execute().body();
        testBoardId = createdBoard.id;
        Assert.assertNotNull(createdBoard, "Created board not found");
        assertEquals(createdBoard.name, testBoardName, "Invalid board was found");

        TrelloList createdTrelloList = client.listsService.createList(testBoardId, testListName).execute().body();
        testListId = createdTrelloList.id;
        Assert.assertNotNull(createdTrelloList, "Created list was not found");
        assertEquals(createdTrelloList.name, testListName, "Invalid list was found");

        Card createdTrelloCard = client.cardsService.createCard(testListId, new Card(testCardName)).execute().body();
        testCardId = createdTrelloCard.id;
        Assert.assertNotNull(createdTrelloCard, "Created card was not found");
        assertEquals(createdTrelloCard.name, testCardName, "Invalid card was found");
    }

    @AfterClass
    public void clearData() throws IOException {
        client.boardsService.deleteBoard(testBoardId).execute();
        Board board = client.boardsService.getBoard(testBoardId).execute().body();
        Assert.assertNull(board, "Deleted board still exists");
    }

    @Test(description = "Test trello api+cookies login")
    @Parameters({"username", "password"})
    public void login(@Optional("") String username,
                      @Optional("") String password) throws IOException {

        new TrelloApiAuth().login(username, password);

        Assert.assertTrue(new BoardsPage().isOpened(), "Boards Page not opened. Auth failed?");
    }

    @Test(description = "Test trello open card", priority = 1)
    public void openCard() {
        BoardPage boardPage = boardsPage.openBoardByName(testBoardName);
        CardPopupPage cardPopupPage = boardPage.openCard(testListName, testCardName);
        Assert.assertTrue(cardPopupPage.isOpened(), "Card Popup not opened.");
    }

    @Test(description = "Test trello change card description", priority = 2)
    public void changeCardDescription() throws IOException {
        CardPopupPage cardPopupPage = new CardPopupPage();

        String testBoardDescription = String.format("Test Card Description - %s", new Date().getTime());
        cardPopupPage.setDescription(testBoardDescription);

        //check from UI
        assertEquals(cardPopupPage.getDescription(), testBoardDescription, "Invalid description saved");

        //check from API
        Card card = client.cardsService.getCard(this.testCardId).execute().body();
        assertEquals(card.desc, testBoardDescription, "Invalid description saved (API check)");
    }

    @Test(description = "Test trello add members", priority = 3)
    @Parameters({"testMemberName"})
    public void addCardMembers(@Optional("Serh") String testMemberName) throws IOException {
        CardPopupPage cardPopupPage = new CardPopupPage();

        cardPopupPage.addMember(testMemberName);

        //check
        List<String> membersNames = cardPopupPage.getMembers();
        Assert.assertTrue(membersNames.stream().anyMatch((s) -> s.startsWith(testMemberName)), "Added member was not found");
    }
    @Test
    public void moveCard() {
        //   cardPopupPage.moveToList(""):

    }

    @Test(description = "Test trello rename card", priority = 3)
    @Parameters({"newCardName"})
    public void rename(@Optional("") String newCardName) throws IOException {

        if (newCardName.isEmpty())
            newCardName = String.format("Test Renamed Card - %s", new Date().getTime());

        CardPopupPage cardPopupPage = new CardPopupPage();
        cardPopupPage.setName(newCardName);

        testCardName = newCardName;

        //check from UI
        Assert.assertEquals(cardPopupPage.getName(), newCardName, "Card is not renamed");

        //check from API
       /* await().atMost(5, TimeUnit.SECONDS)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .until(()->assertEquals(newCardName,
                        client.cardsService.getCard(this.testCardId).execute().body().name)
        );*/
    }


}
package tasks;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Board;
import com.trello.api.models.Card;
import com.trello.api.models.TrelloList;
import com.trello.api.services.LabelColor;
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
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
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
    @Parameters({"newCardDescription"})
    public void changeCardDescription(@Optional("") String newCardDescription) throws IOException {
        CardPopupPage cardPopupPage = new CardPopupPage();

        if (newCardDescription.isEmpty())
            newCardDescription = String.format("Test Card Description - %s", new Date().getTime());

        cardPopupPage.setDescription(newCardDescription);

        //wait and check from API
        final String cardDescription = newCardDescription;
        await().atMost(5, TimeUnit.SECONDS)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .until(() -> cardDescription.equals(
                        client.cardsService.getCard(this.testCardId).execute().body().desc)
                );

        //check from UI
        assertEquals(cardPopupPage.getDescription(), newCardDescription, "Invalid description saved");
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
    public void rename(@Optional("") String newCardName) throws NullPointerException {

        if (newCardName.isEmpty())
            newCardName = String.format("Test Renamed Card - %s", new Date().getTime());

        CardPopupPage cardPopupPage = new CardPopupPage();
        cardPopupPage.setName(newCardName);

        //wait and check from API
        final String cardName = newCardName;
        await().atMost(5, TimeUnit.SECONDS)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .until(() -> cardName.equals(
                        client.cardsService.getCard(this.testCardId).execute().body().name)
                );

        //check from UI
        Assert.assertEquals(cardPopupPage.getName(), newCardName, "Card is not renamed");

        testCardName = newCardName;
    }

    @Test(description = "Test trello add card label", priority = 3)
    @Parameters({"testLabelColor"})
    public void addCardLabel(@Optional("BLUE") LabelColor testLabelColor) throws IOException {
        CardPopupPage cardPopupPage = new CardPopupPage();

        cardPopupPage.addLabel(testLabelColor);

        //check on UI
        List<LabelColor> labelColors = cardPopupPage.getLabels();
        Assert.assertTrue(labelColors.stream().anyMatch((s) -> s.toString().startsWith(testLabelColor.toString())), "Added label was not found");

        //wait and check from API
        final LabelColor labelColor = testLabelColor;
        await().atMost(5, TimeUnit.SECONDS)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .until(() -> labelColor.toString().equals(
                        client.cardsService.getCard(this.testCardId).execute().body().labels[0].color)
                );
    }



}
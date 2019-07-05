package regression;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Board;
import com.trello.api.models.TrelloList;
import com.trello.ui.core.BrowserFactory;
import com.trello.ui.core.credentialsstorage.Credentials;
import com.trello.ui.core.credentialsstorage.CredentialsStorage;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.CardPage;
import com.trello.ui.pages.LoginPage;
import junk.TryLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Date;

import static com.trello.ui.core.Constants.credentialsStorageFilePath;

/**
 * Created by horobets on Jul 05, 2019
 */
public class CardActionsTests extends BrowserFactory {

    public TrelloRestClient client = new TrelloRestClient();
    public LoginPage loginPage = new LoginPage();
    public BoardsPage boardsPage = new BoardsPage();
    public CardPage cardPage = new CardPage();
    private Logger logger = LoggerFactory.getLogger(TryLogger.class);
    private String testBoardId = "";
    private String testBoardName = String.format("Test Board - %s", new Date().getTime());
    private String testListId = "";
    private String testListName = String.format("Test List - %s", new Date().getTime());
    private String testCardId = "";
    private String testCardName = String.format("Test Card - %s", new Date().getTime());

    //Card card = new Card("Test_Card_" + new Date().getTime());

    @BeforeClass
    public void prepareData() throws IOException {
        Board createdBoard = client.boardsService.createBoard(testBoardName).execute().body();
        testBoardId = createdBoard.id;
        Assert.assertNotNull(createdBoard, "Created board not found");
        Assert.assertEquals(createdBoard.name, testBoardName, "Invalid board was found");

        TrelloList createdTrelloList = client.listsService.createList(testBoardId, testListName).execute().body();
        testListId = createdTrelloList.id;
        Assert.assertNotNull(createdTrelloList, "Created list was not found");
        Assert.assertEquals(createdTrelloList.name, testListName, "Invalid list was found");
    }

    @AfterClass
    public void clearData() throws IOException {
        client.boardsService.deleteBoard(testBoardId).execute();
        Board board = client.boardsService.getBoard(testBoardId).execute().body();
        Assert.assertNull(board, "Deleted board still exists");
    }

    @Parameters({"username", "password"})
    @Test(description = "Test trello api+cookies login")
    public void login(@Optional("") String username,
                      @Optional("") String password) throws IOException {

        //use credentialsstorage if no credentials provided
        if (username.isEmpty() || password.isEmpty()) {
            Credentials trelloCredentials = (new CredentialsStorage(credentialsStorageFilePath)).getLastCredentials();
            username = trelloCredentials.getUsername();
            password = trelloCredentials.getPassword();
        }


        new LoginTest().login(username, password);

        //loginPage.login(username, password);

        boardsPage.openBoardByUrlName("jacksparrowtitle");
    }

    @Test
    public void openCard() {
        cardPage.open("");
    }

    @Test
    public void moveCard() {
        //   cardPage.moveToList(""):

    }

    @Test
    public void rename() {

    }


}
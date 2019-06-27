package regression;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Card;
import com.trello.ui.core.BrowserFactory;
import com.trello.ui.core.credentialsstorage.Credentials;
import com.trello.ui.core.credentialsstorage.CredentialsStorage;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.CardPage;
import com.trello.ui.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

import static com.trello.ui.core.Constants.credentialsStorageFilePath;

public class CardActions extends BrowserFactory {

    public TrelloRestClient client = new TrelloRestClient();

    public LoginPage loginPage = new LoginPage();
    public BoardsPage boardsPage = new BoardsPage();
    public CardPage cardPage = new CardPage();

    Card card = new Card("Test_Card_" + new Date().getTime());

    @BeforeTest
    public void prepareData() throws IOException {
        card = client.cardsService.createCard("5d147de6ba27175a5e71cf72", card).execute().body();
    }

    @AfterTest
    public void clearData() throws IOException {
        client.cardsService.deleteCard(card.id).execute();
    }

    @Test
    public void login() {
        loginPage.open();
        //loginPage.login("loliktestintegration@gmail.com", "iLoveBieber");

        Credentials trelloCredentials = (new CredentialsStorage(credentialsStorageFilePath)).getLastCredentials();
        String username = trelloCredentials.getUsername();
        String password = trelloCredentials.getPassword();

        loginPage.login(username, password);

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
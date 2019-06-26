package junk;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Card;
import org.testng.annotations.Test;

import java.io.IOException;

public class TryTrelloApi {

    TrelloRestClient client = new TrelloRestClient();


    @Test
    public void callIt() throws IOException, InterruptedException {
        Card card = new Card();
        card.name = "My New CARD 2";
        Card createdCard = client.cardsService.createCard("5d1243d7c2b5517f63f3a07a", card).execute().body();
        client.cardsService.deleteCard(createdCard.id).execute();
    }

}
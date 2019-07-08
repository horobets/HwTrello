package com.trello.ui.pages;

import com.trello.ui.core.Elem;
import org.openqa.selenium.By;

public class CardPopupPage extends TrelloBasePage {

    public Elem nameField = new Elem(By.cssSelector(".js-card-detail-title-input"), "Card Name Field");
    public Elem descriptionField = new Elem(By.cssSelector(".description-fake-text-area"), "Card Description Field");
    public Elem commentField = new Elem(By.cssSelector(".comment-box-input"), "Card Comment Field");

    //add to card
    public Elem changeCardMembersButton = new Elem(By.cssSelector(".js-change-card-members"), "Change card members Button");
    public Elem addChecklistMenuButton = new Elem(By.cssSelector(".js-add-checklist-menu"), "Add checklist menu Button");
    public Elem addDueDateButton = new Elem(By.cssSelector(".js-add-due-date"), "Add due date Button");
    public Elem attachmentButton = new Elem(By.cssSelector("[class='button-link js-attach']"), "Add attachment Button");
    //Power-ups
    public Elem getPowerUpsButton = new Elem(By.cssSelector(".js-get-pups"), "Get power-ups Button");
    //Actions
    public Elem moveCardButton = new Elem(By.cssSelector(".js-move-card"), "Move Card  Button");
    public Elem boardNameSelect = new Elem(By.cssSelector(".js-select-board"), "Board Name Select");
    public Elem listNameSelect = new Elem(By.cssSelector(".js-select-list"), "List Name Select");
    public Elem positionSelect = new Elem(By.cssSelector(".js-select-position"), "Position Select");
    public Elem moveCardSubmitButton = new Elem(By.cssSelector(".js-submit"), "Move Card Submit button");
    public Elem copyCardButton = new Elem(By.cssSelector(".js-copy-card"), "Copy Card  Button");
    public Elem watchCardButton = new Elem(By.cssSelector(".js-subscribe"), "Watch Button");
    public Elem unwatchCardButton = new Elem(By.cssSelector(".js-unsubscribe"), "UnWatch Button");
    public Elem archiveCardButton = new Elem(By.cssSelector(".js-archive-card"), "Archive card Button");
    public Elem shareCardButton = new Elem(By.cssSelector(".js-more-menu"), "Share card Button");

    public void open(String cardName) {

    }

    @Override
    public boolean isOpened() {
        return nameField.isPresent();
    }

    public void move(String listName) {

    }


}
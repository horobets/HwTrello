package com.trello.ui.pages;

import com.trello.ui.core.Elem;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class CardPopupPage extends TrelloBasePage {

    public Elem nameField = new Elem(By.cssSelector(".window textarea.js-card-detail-title-input"), "Card Name Field");
    public Elem nameLabel = new Elem(By.cssSelector(".window .window-title > h2"), "Card Name Label");
    //description
    public Elem descriptionField = new Elem(By.cssSelector(".window .card-description"), "Card Description Field");
    public Elem descriptionSaveButton = new Elem(By.cssSelector(".description-edit [type='submit']"), "Card Description Save Button");
    public Elem descriptionLabel = new Elem(By.cssSelector(".window .js-desc"), "Card Saved Description Label");
    //comment
    public Elem commentField = new Elem(By.cssSelector(".comment-box-input"), "Card Comment Field");

    //add to card
    //members
    public Elem membersListItem = new Elem(By.cssSelector(".window .js-card-detail-members-list .member-initials"), "Members list items");
    public Elem changeCardMembersButton = new Elem(By.cssSelector(".js-change-card-members"), "Change card members Button");
    public Elem membersPopupSearchField = new Elem(By.cssSelector("input.js-search-mem"), "Members search field");
    public Elem membersPopupListItem = new Elem(By.cssSelector("a.js-select-member"), "Members list item");//use title attribute to get name
    public Elem membersPopupCloseButton = new Elem(By.cssSelector(".pop-over-header-close-btn"), "Members popup close button");
    //
    public Elem addChecklistMenuButton = new Elem(By.cssSelector(".js-add-checklist-menu"), "Add checklist menu Button");
    public Elem addDueDateButton = new Elem(By.cssSelector(".js-add-due-date"), "Add due date Button");
    public Elem attachmentButton = new Elem(By.cssSelector("[class='button-link js-attach']"), "Add attachment Button");
    //Power-ups
    public Elem getPowerUpsButton = new Elem(By.cssSelector(".js-get-pups"), "Get power-ups Button");
    //Actions
    public Elem moveCardButton = new Elem(By.cssSelector(".js-move-card"), "Move Card  Button");
    //move popup
    public Elem boardNameSelect = new Elem(By.cssSelector(".js-select-board"), "Board Name Select");
    public Elem listNameSelect = new Elem(By.cssSelector(".js-select-list"), "List Name Select");
    public Elem positionSelect = new Elem(By.cssSelector(".js-select-position"), "Position Select");
    public Elem moveCardSubmitButton = new Elem(By.cssSelector(".js-submit"), "Move Card Submit button");
    //
    public Elem copyCardButton = new Elem(By.cssSelector(".js-copy-card"), "Copy Card  Button");
    public Elem watchCardButton = new Elem(By.cssSelector(".js-subscribe"), "Watch Button");
    public Elem unwatchCardButton = new Elem(By.cssSelector(".js-unsubscribe"), "UnWatch Button");
    public Elem archiveCardButton = new Elem(By.cssSelector(".js-archive-card"), "Archive card Button");
    public Elem shareCardButton = new Elem(By.cssSelector(".js-more-menu"), "Share card Button");

    public void open(String cardName) {

    }

    @Override
    public boolean isOpened() {
        return nameField.isPresent() && nameField.isVisible();
    }

    public String getName() {
        String t = nameLabel.readText();
        return nameLabel.readText();
    }

    public void setName(String newName) {
        nameField.click();
        nameField.type(newName);
    }

    public String getDescription() {
        return descriptionLabel.readText();
    }

    public void setDescription(String newDescription) {
        descriptionField.click();
        descriptionField.type(newDescription);
        descriptionSaveButton.click();
    }

    public void addMember(String memberName) {
        changeCardMembersButton.click();
        membersPopupSearchField.type(memberName);
        membersPopupListItem.isVisible();
        membersPopupListItem.click();
        membersPopupCloseButton.click();
    }

    public List<String> getMembers() {
        List<String> membersNames = new ArrayList<>();
        membersListItem.findAll().forEach(webElement -> membersNames.add(webElement.getAttribute("title")));
        return membersNames;
    }

    public void move(String listName) {

    }


}
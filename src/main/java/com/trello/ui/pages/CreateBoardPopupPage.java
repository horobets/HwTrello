package com.trello.ui.pages;

import org.openqa.selenium.By;

public class CreateBoardPopupPage extends TrelloBasePage {

    private By boardNameImputBy = By.cssSelector(".subtle-input");

    /*public CreateBoardPopupPage(WebDriver driver) {
        super(driver);
    }*/


    @Override
    public boolean isOpened() {
        return isElementPresent(boardNameImputBy, 5);
    }

    //public void createBoard(String boardName)
}

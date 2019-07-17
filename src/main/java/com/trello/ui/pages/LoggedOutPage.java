package com.trello.ui.pages;

import org.openqa.selenium.By;

public class LoggedOutPage extends TrelloBasePage {

    private By loginButtonBy = By.cssSelector("[href='/login']");


    public boolean isOpened() {
        return isElementPresent(loginButtonBy, 5);
    }
}

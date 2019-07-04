package com.trello.ui.pages;

import org.openqa.selenium.By;

public class LoggedOutPage extends TrelloBasePage {

    private By loginButtonBy = By.cssSelector("[href='/login']");

    /*public LoggedOutPage(WebDriver driver) {
        super(driver);
    }*/

    public boolean isOpened() {
        return isElementPresent(loginButtonBy, 5);
    }
}

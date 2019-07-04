package com.trello.ui.pages;

import com.trello.ui.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.trello.ui.core.BrowserFactory.driver;


public abstract class TrelloBasePage extends BasePage {


    private By userButtonBy = By.cssSelector(".member");
    private By logoutMenuitemBy = By.cssSelector(".js-logout");
    private By boardsButton = By.cssSelector("[data-test-id='header-boards-menu-button']");
    private By homeButton = By.cssSelector("[name='house']");

    /*public TrelloBasePage(WebDriver driver) {
        super(driver);
    }*/


    public void logOut() {
        isElementPresent(userButtonBy, 5);
        click(userButtonBy);

        isElementPresent(logoutMenuitemBy, 5);
        click(logoutMenuitemBy);

        new WebDriverWait(driver(), 10).until(ExpectedConditions.urlToBe("https://trello.com/logged-out"));
    }

    public BoardsPage openBoardsPage() {
        isElementPresent(homeButton, 5);
        click(homeButton);
        BoardsPage boardsPage = new BoardsPage(/*driver*/);
        boardsPage.isOpened();
        return boardsPage;
    }
}

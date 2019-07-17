package com.trello.ui.pages;

import org.openqa.selenium.By;

public class BoardMenuPage extends TrelloBasePage {
    private By boardMenuHeader = By.cssSelector(".board-menu-header-content");
    private By menuMore = By.cssSelector(".js-open-more");
    private By menuCloseBoard = By.cssSelector(".js-close-board");

    private By deleteLink = By.cssSelector(".js-delete");
    private By menuConfirm = By.cssSelector(".js-confirm");


    @Override
    public boolean isOpened() {
        return isElementVisible(boardMenuHeader, 2);
    }

    public void clickMore() {
        click(menuMore);
    }

    public void clickCloseBoard() {
        click(menuCloseBoard);
    }


    public void clickDelete() {
        isElementVisible(deleteLink, 5);
        click(deleteLink);
    }

    public void clickConfirm() {
        click(menuConfirm);
    }
}

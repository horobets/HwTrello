package com.trello.ui.pages;

import com.trello.ui.core.Elem;
import org.openqa.selenium.By;

public class BoardsPage extends TrelloBasePage {

    public Elem createNewBoardBtn = new Elem(By.cssSelector(".mod-add"), "createNewBoard button");
    public Elem boardTitleFld = new Elem(By.cssSelector(".subtle-input"), "boardTitleFld field");
    public Elem createBoardBtn = new Elem(By.cssSelector(".primary"), "createBoardBtn button");

    private String boardListItemXpathFormat = "//div[@class='board-tile-details-name']/div[text()='%s']";


    public Elem boardByUrlName(String urlName) {
        return new Elem(By.cssSelector(".board-tile[href*='" + urlName + "']"), urlName);
    }

    public BoardPage openBoardByUrlName(String urlName) {
        boardByUrlName(urlName).click();


        BoardPage boardPage = new BoardPage();
        if (!boardPage.isOpened())
            return null;
        return boardPage;
    }

    public boolean isOpened() {

        return createNewBoardBtn.isPresent() && createNewBoardBtn.isVisible();
    }

    public BoardPage createBoard(String name) {
        createNewBoardBtn.click();
        boardTitleFld.type(name);
        createBoardBtn.click();

        BoardPage boardPage = new BoardPage();
        boardPage.isOpened();
        return boardPage;
    }

    public BoardPage openBoardByName(String name) {

        isBoardListed(name);

        By boardElement = By.xpath(String.format(boardListItemXpathFormat, name));
        waitVisibility(boardElement);
        find(boardElement).click();

        BoardPage boardPage = new BoardPage();
        if (!boardPage.isOpened())
            return null;
        return boardPage;
    }

    public boolean isBoardListed(String name) {

        return isElementPresent(By.xpath(String.format(boardListItemXpathFormat, name)), 3);
    }
}
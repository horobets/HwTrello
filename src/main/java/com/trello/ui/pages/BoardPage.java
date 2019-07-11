package com.trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by horobets on Jun 20, 2019
 */
public class BoardPage extends TrelloBasePage {
    private By boardEditingBy = By.cssSelector(".js-board-editing-target");
    private By openMenuLinkBy = By.cssSelector(".js-show-sidebar");
    private By iconStarBy = By.cssSelector(".icon-star");
    private By visibilityMenuButtonBy = By.cssSelector("#permission-level");
    private By visibilityMenuitemPublicBy = By.cssSelector(".pop-over-list [name='public']");
    private By visibilityMenuitemPrivateBy = By.cssSelector(".pop-over-list [name='private']");
    private By visibilityPublicConfirmationPopupButtonBy = By.cssSelector(".make-public-confirmation-button");

    private String cardLinkXpathFormat = "//span[contains(@class, 'js-card-name') and contains(text(), '%s')]";
    private String listHeaderXpathFormat = "//textarea[contains(@class, 'list-header-name') and contains(text(), '%s')]";
    private String listBlockXpathFormat = "//div[@class='list js-list-content']//textarea[contains(@class, 'list-header-name') and contains(text(), '%s')]";

    /*public BoardPage(WebDriver driver) {
        super(driver);
    }*/

    @Override
    public boolean isOpened() {
        return isElementPresent(boardEditingBy, 5) && isElementVisible(boardEditingBy, 5);
    }


    public void deleteBoard() {

        openMenu();

        BoardMenuPage boardMenuPage = new BoardMenuPage();
        boardMenuPage.isOpened();
        boardMenuPage.clickMore();
        boardMenuPage.clickCloseBoard();
        boardMenuPage.clickConfirm();
        boardMenuPage.clickDelete();
        boardMenuPage.clickConfirm();

        isElementPresent(By.cssSelector(".little-message"), 5);

    }

    public boolean isMenuOpened() {
        return new BoardMenuPage().isOpened();
    }

    public void openMenu() {
        if (!isMenuOpened())
            click(openMenuLinkBy);
    }

    public void clickStar() {
        click(iconStarBy);
    }

    public void changeVisibility(BoardVisibility boardVisibility) {

        openVisibilityMenu();
        switch (boardVisibility) {
            case PUBLIC:
                click(visibilityMenuitemPublicBy);
                click(visibilityPublicConfirmationPopupButtonBy);
                break;
            case PRIVATE:
                click(visibilityMenuitemPrivateBy);
                break;
        }
    }

    public void openVisibilityMenu() {
        click(visibilityMenuButtonBy);
    }

    public CardPopupPage openCard(String listName, String cardName) {

        WebElement listElement = find(By.xpath(String.format(listBlockXpathFormat, listName)));

        WebElement cardElement = listElement.findElement(By.xpath(String.format(cardLinkXpathFormat, cardName)));
        cardElement.click();

        CardPopupPage cardPopupPage = new CardPopupPage();
        if (!cardPopupPage.isOpened())
            return null;
        return cardPopupPage;
    }

    public CardPopupPage openCard(String cardName) {

        WebElement cardElement = find(By.xpath(String.format(cardLinkXpathFormat, cardName)));
        cardElement.click();

        CardPopupPage cardPopupPage = new CardPopupPage();
        if (!cardPopupPage.isOpened())
            return null;
        return cardPopupPage;
    }
}

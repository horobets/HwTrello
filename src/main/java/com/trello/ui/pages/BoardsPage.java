package com.trello.ui.pages;

import com.trello.ui.core.Elem;
import org.openqa.selenium.By;

/**
 * Created by horobets on Jun 20, 2019
 */
public class BoardsPage {

    private static final String PATH = "loloktest4/boards";

    private Elem boardByUrlName(String urlName) {
        return new Elem(By.cssSelector(".board-tile[href*=[" + urlName + "]"));
    }

    public void Open() {

    }

    public void isOpened() {

    }

    public void openBoard(String urlName) {
        new Elem(By.cssSelector(".board-tile[href*=["))
    }
}

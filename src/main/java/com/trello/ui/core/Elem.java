package com.trello.ui.core;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

import static com.trello.ui.core.BrowserFactory.driver;
import static com.trello.ui.core.BrowserFactory.getWebDriverWait;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

/**
 * Created by horobets on Jun 20, 2019
 */
public class Elem {

    private By by;
    private String name;

    public Elem(By by, String name) {
        this.by = by;
        this.name = name;
    }

    public Elem(By by) {
        this(by, "");
    }


    public WebElement find() {
        return getWebDriverWait(10).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> findAll() {
        return getWebDriverWait(10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    @Step
    public void click() {
        find().click();
    }

    @Step
    public void type(String text) {
        find().clear();
        find().sendKeys(text);
    }

    public boolean isPresent() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public boolean isVisible() {
        Wait wait = new FluentWait(driver())
                .withTimeout(ofSeconds(10))
                .pollingEvery(ofMillis(500))
                .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String readText() {
        return find().getText();
    }

    public void selectItem(String itemText) {
        Select dropdown = new Select(find());
        dropdown.selectByVisibleText(itemText);
    }

    public void selectItem(int itemIndex) {
        Select dropdown = new Select(find());
        dropdown.selectByIndex(itemIndex);
    }
}
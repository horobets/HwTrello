package com.trello.ui.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static com.trello.ui.core.BrowserFactory.driver;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public abstract class BasePage {
    /*protected WebDriver driver;

    public BasePage(WebDriver driver) {
            this.driver = driver;
        }*/

    //public abstract void goToPage();

    public abstract boolean isOpened();
    /*protected boolean isOpened(By pageElement) {
        return isElementPresent(pageElement, 10);
    }*/

    public void waitVisibility(By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver(), 15);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public void click(By elementBy) {
        waitVisibility(elementBy);

        WebDriverWait wait = new WebDriverWait(driver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(elementBy)).click();
    }

    public void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        driver().findElement(elementBy).clear();
        driver().findElement(elementBy).sendKeys(text);
    }

    public String readText(By elementBy) {
        waitVisibility(elementBy);
        return driver().findElement(elementBy).getText();
    }

    public void selectItem(By dropdownElement, String itemText) {
        Select genderDropdown = new Select(driver().findElement(dropdownElement));
        //try {
        genderDropdown.selectByVisibleText(itemText);
        /*} catch (NoSuchElementException ex) {
            System.err.printf("Can't select an item: %s %n", itemText);
        }*/
    }

    public void selectItem(By dropdownElement, int itemIndex) {
        Select genderDropdown = new Select(driver().findElement(dropdownElement));
        //try {
        genderDropdown.selectByIndex(itemIndex);
        /*} catch (NoSuchElementException ex) {
            System.err.printf("Can't select an item: %s %n", itemText);
        }*/
    }

    public boolean isElementPresent(final By locator, long seconds) {
        Wait wait = new FluentWait(driver())
                .withTimeout(ofSeconds(seconds))
                .pollingEvery(ofMillis(500))
                .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementVisible(final By locator, long seconds) {
        Wait wait = new FluentWait(driver())
                .withTimeout(ofSeconds(seconds))
                .pollingEvery(ofMillis(500))
                .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitInvisibility(final By locator, long seconds) {
        Wait wait = new FluentWait(driver())
                .withTimeout(ofSeconds(seconds))
                .pollingEvery(ofMillis(500))
                .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            //return true;
        } catch (TimeoutException e) {
            //return false;
        }
    }

    public void switchToFrame(By frameToSwitchTo) {
        driver().switchTo().defaultContent();

        FluentWait fwait = new WebDriverWait(driver(), 10)
                .withTimeout(ofSeconds(15)).pollingEvery(ofMillis(500))
                .ignoring(ElementNotVisibleException.class, NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        fwait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameToSwitchTo));
    }

    public WebElement find(By by) {
        WebDriverWait wait = new WebDriverWait(driver(), 10);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void type(By by, String text) {
        find(by).clear();
        find(by).sendKeys(text);
    }
}

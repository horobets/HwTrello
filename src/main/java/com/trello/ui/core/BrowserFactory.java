package com.trello.ui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by horobets on Jun 20, 2019
 */
public class BrowserFactory {

    private Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
    private static WebDriver driver;

    public static WebDriver driver() {
        return driver;
    }

    public static void get(String url) {
        driver().get(url);
    }

    public static WebDriverWait getWebDriverWait(long timeout) {
        return new WebDriverWait(driver(), timeout);
    }

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        logger.info("BROWSER STARTED");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        logger.info("BROWSER CLOSED");

    }

}
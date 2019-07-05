package com.trello.ui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.HashSet;

/**
 * Created by horobets on Jun 20, 2019
 */
public class BrowserFactory {

    protected HashSet<String> cookies = new HashSet<>();


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

    @BeforeSuite
    public void setUp() {
        //driver = new ChromeDriver();
        //logger.info("BROWSER STARTED");


        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();


        //driver.manage().timeouts().pageLoadTimeout(0, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        //driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        driver = new EventFiringWebDriver(driver).register(new DriverEventListener());
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        logger.info("BROWSER CLOSED");

    }

}
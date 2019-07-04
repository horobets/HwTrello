package com.trello.ui.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TakeScreenshot {
    public static void takeScreenshot(WebDriver webdriver, String fileWithPath) {
        File screenshotFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(fileWithPath));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }

}

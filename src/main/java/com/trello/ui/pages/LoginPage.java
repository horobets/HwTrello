package com.trello.ui.pages;

import com.trello.ui.core.Constants;
import com.trello.ui.core.Elem;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.trello.ui.core.BrowserFactory.driver;
import static com.trello.ui.core.BrowserFactory.get;

/**
 * Created by horobets on Jun 20, 2019
 */
public class LoginPage {

    private static final String PATH = "login";

    public Elem emailFld = new Elem(By.cssSelector("#user"), "Login Field");
    public Elem passFld = new Elem(By.cssSelector("#password"), "Password Field");
    public Elem loginBtn = new Elem(By.cssSelector("#login"), "Login Button");

    @Step("Open Page: " + PATH)
    public void open() {
        get(Constants.URL + PATH);
        Assert.assertTrue(isOpened(), "Page 'Login' [" + PATH + "] not Opened");
    }

    public boolean isOpened() {
        return loginBtn.isPresent() && driver().getCurrentUrl().equals(Constants.URL + PATH);
    }

    @Step
    public void login(String email, String password) {
        emailFld.type(email);
        passFld.type(password);
        loginBtn.click();
    }


}
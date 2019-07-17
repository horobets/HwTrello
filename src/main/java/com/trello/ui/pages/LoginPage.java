package com.trello.ui.pages;

import com.trello.ui.core.Constants;
import com.trello.ui.core.Elem;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.trello.ui.core.BrowserFactory.driver;
import static com.trello.ui.core.BrowserFactory.get;


public class LoginPage extends TrelloBasePage {

    private static final String PATH = "login";

    private Elem emailFld = new Elem(By.cssSelector("#user"), "Login Field");
    private Elem passFld = new Elem(By.cssSelector("#password"), "Password Field");
    private Elem loginBtn = new Elem(By.cssSelector("#login"), "Login Button");
    private Elem errorMessage = new Elem(By.cssSelector("p.error-message"), "Error Message");

    @Step("Open Page: " + PATH)
    public void open() {
        get(Constants.URL + PATH);
        Assert.assertTrue(isOpened(), "Page 'Login' [" + PATH + "] not Opened");
    }

    @Override
    public boolean isOpened() {
        return loginBtn.isPresent() && driver().getCurrentUrl().equals(Constants.URL + PATH);
    }

    @Step
    public void login(String email, String password) {
        emailFld.type(email);
        passFld.type(password);
        loginBtn.click();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isVisible();
    }
}
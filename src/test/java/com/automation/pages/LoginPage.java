package com.automation.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    //LOCATORS
    private By emailField = By.cssSelector("input[data-qa='login-email']");
    private By passwordField = By.cssSelector("input[data-qa='login-password']");
    private By loginButton = By.cssSelector("button[data-qa='login-button']");
    private By loginHeader = By.xpath("//h2[text()='Login to your account']");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //ACTIONS
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
    public boolean isLoginHeaderVisible() {
        return driver.findElement(loginHeader).isDisplayed();
    }
}

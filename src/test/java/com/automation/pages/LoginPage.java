package com.automation.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    //LOCATORS
    private By emailField = By.cssSelector("input[data-qa='login-email']");
    private By passwordField = By.cssSelector("input[data-qa='login-password']");
    private By loginButton = By.cssSelector("button[data-qa='login-button']");
    private By loginHeader = By.xpath("//h2[text()='Login to your account']");
    private By errorMessage = By.cssSelector("form[action='/login'] p");
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
    public String getErrorMessage() {
        return wait.until(driver->driver.findElement(errorMessage)).getText();
    }
    public String getEmailValidationMessage() {
    WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
    return emailElement.getAttribute("validationMessage");
}
}

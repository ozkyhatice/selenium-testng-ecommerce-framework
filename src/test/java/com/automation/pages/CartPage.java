package com.automation.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class CartPage extends BasePage{
    private By firstProductName = By.cssSelector(".cart_description h4 a");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public String getFirstProductName() {
        return wait.until(driver-> driver.findElement(firstProductName)).getText();
    }
    
}

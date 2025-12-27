package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By searchBox = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By productsLink = By.linkText("Products");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToProducts() {
        driver.findElement(productsLink).click();
    }

    public void searchFor(String text) {
        driver.findElement(searchBox).sendKeys(text);
        driver.findElement(searchButton).click();
    }

    public boolean isPageOpened() {
        return driver.getTitle().contains("Automation Exercise");
    }
}

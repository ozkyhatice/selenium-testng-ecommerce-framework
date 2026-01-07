package com.automation.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {

    //LOCATORS
    private By allProductsText = By.xpath("//h2[text()='All Products']");
    private By searchResultNames = By.cssSelector(".productinfo p");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }
    public boolean isProductPageVisible() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsText)).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}
    public List<WebElement> getSearchResultList() {
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultNames));
    return driver.findElements(searchResultNames);
}
}
    


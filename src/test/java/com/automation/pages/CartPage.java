package com.automation.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

public class CartPage extends BasePage{
    private By firstProductName = By.cssSelector(".cart_description h4 a");
    private By deleteButton = By.className("cart_quantity_delete");
    private By cartRows = By.cssSelector(".cart_info tbody tr");
    private By checkoutButton = By.className("check_out");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public String getFirstProductName() {
        return wait.until(driver-> driver.findElement(firstProductName)).getText();
    }
    public void deleteItemFromCart() {
        click(deleteButton);
    }
    public boolean isProductRemoved() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(cartRows));
    }
    public void clickCheckout() {
        clickWithJS(checkoutButton);
    }
}

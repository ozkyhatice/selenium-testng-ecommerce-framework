package com.automation.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class CheckoutPage extends BasePage{
    private By commentTextArea = By.name("message");
    private By placeOrderButton = By.cssSelector("a.check_out");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public void enterComment(String comment) {
        sendKeys(commentTextArea, comment);
    }
    public void placeOrder() {
        clickWithJS(placeOrderButton);
    }
    public void removeAdsWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var ads = document.getElementsByClassName('adsbygoogle');" +
                         "for(var i=0; i<ads.length; i++) {" +
                         "ads[i].style.display='none';" +
                         "}");
    }
}

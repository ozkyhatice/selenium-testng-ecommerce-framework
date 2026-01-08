package com.automation.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage{
    private By nameOnCard = By.name("name_on_card");
    private By cardNumber = By.name("card_number");
    private By cvc = By.name("cvc");
    private By expiryMonth = By.name("expiry_month");
    private By expiryYear = By.name("expiry_year");
    private By payButton = By.id("submit");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }
    public void fillPaymentDetails(String name, String number, String cvcCode, String month, String year) {
        sendKeys(nameOnCard, name);
        sendKeys(cardNumber, number);
        sendKeys(cvc, cvcCode);
        sendKeys(expiryMonth, month);
        sendKeys(expiryYear, year);
    }
    public void clickPayButton() {
        clickWithJS(payButton); 
    }
}

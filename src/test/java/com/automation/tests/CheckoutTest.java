package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutTest extends BaseTest {
    @DataProvider(name = "userCredentials")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"test2@test.com.tr", "1234"},   
        };
    }
    @Test(dataProvider = "userCredentials", retryAnalyzer = com.automation.utils.Retry.class)
    public void verifyOrderPlacement(String email, String password) {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);

        //Login
        homePage.navigateToSignupLogin();
        loginPage.login(email, password);
        //Add product to cart
        homePage.navigateToProducts();
        driver.navigate().refresh();
        productsPage.addFirstProductToCart();
        productsPage.navigateToViewCart();
        //Proceed to checkout
        cartPage.clickCheckout();
        //remove ads
        checkoutPage.removeAdsWithJS();
        checkoutPage.enterComment("comment for order");
        checkoutPage.placeOrder();
        //Payment
        paymentPage.fillPaymentDetails("Test User", "4111111111111111", "12", "2025", "123");
        paymentPage.clickPayButton();

        //Verification
        softAssert.assertTrue(driver.getCurrentUrl().contains("payment_done"),
                "Order confirmation page is not displayed.");
        softAssert.assertAll();
    }
}

package com.automation.tests;
import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.ProductsPage;
import com.automation.pages.CartPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest{
    @Test
    public void verifyProductAddedToCart() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        // navigate products page and refresh to avoid stale element issues
        homePage.navigateToProducts();
        driver.navigate().refresh();
        String expectedProductName = productsPage.getFirstProductName();

        //add first product to cart and navigate to cart
        productsPage.addFirstProductToCart();
        productsPage.navigateToViewCart();

        String actualProductName = cartPage.getFirstProductName();
        softAssert.assertEquals(actualProductName.toLowerCase(), expectedProductName.toLowerCase(),
                "Product in cart does not match the added product.");


    }
}

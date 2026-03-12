package com.automation.tests;
import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.automation.pages.LoginPage;

public class LoginBeforeCheckout extends BaseTest {
    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][] {
                {"test2@test.com.tr", "1234"}
        };
    }
    @Test(dataProvider = "userData", retryAnalyzer = com.automation.utils.Retry.class)
    public void verifyLoginBeforeCheckout(String email, String password) {
        SoftAssert softAssert = new SoftAssert();
        HomePage homepage = new HomePage(driver);
        //verify user is on homepage
        softAssert.assertTrue(homepage.isPageOpened(), "Home page is not visible");
        LoginPage loginPage = new LoginPage(driver);
        //login with valid credentials
        
        homepage.navigateToSignupLogin();
        softAssert.assertTrue(loginPage.isLoginHeaderVisible(), "Login header is not visible");
        loginPage.login(email, password);
        loginPage.isLoggedIn();
        //Verify 'Logged in as username' at top
        // softAssert.assertTrue(loginPage.getLoggedInUserName().contains("Logged in as"), "Logged in as username is not visible at top");
        //add product to cart and verify user is still logged in
        homepage.navigateToProducts();

        softAssert.assertAll();
    }
}
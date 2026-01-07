package com.automation.tests;
import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class LoginTest extends BaseTest {
    
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"test2@test.com.tr", "1234"},
            
        };
    }
    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password) {
        SoftAssert softAssert = new SoftAssert();

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        softAssert.assertTrue(homePage.isPageOpened(), "Home page did not open");
        homePage.navigateToSignupLogin();
        softAssert.assertTrue(loginPage.isLoginHeaderVisible(), "Login header is not visible");
        loginPage.login(email, password);
        softAssert.assertAll();
    }
    
}

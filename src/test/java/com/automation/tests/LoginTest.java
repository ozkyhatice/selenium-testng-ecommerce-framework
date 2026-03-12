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

    @DataProvider(name = "invalidLoginData")
    public Object[][] getNegativeLoginData() {
        return new Object[][] {
            {"wrong@email.com", "wrongpassword", "Your email or password is incorrect!"},
            {"test2@test.com.tr", "wrongpassword", "Your email or password is incorrect!"},
        };
    }
    @DataProvider(name = "html5ValidatiobData")
    public Object[][] getValidationData() {
        return new Object[][] {
            {"wrongformat", "wrongpassword"},
            {"test2@test.com.tr", "wrongpassword"},
        };
    }

    @Test(dataProvider = "loginData" , retryAnalyzer = com.automation.utils.Retry.class)
    public void loginTest(String email, String password) {
        SoftAssert softAssert = new SoftAssert();

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        softAssert.assertTrue(homePage.isPageOpened(), "Home page did not open");
        homePage.navigateToSignupLogin();
        softAssert.assertTrue(loginPage.isLoginHeaderVisible(), "Login header is not visible");
        loginPage.login(email, password);
        
    }

    @Test(dataProvider = "invalidLoginData", retryAnalyzer = com.automation.utils.Retry.class)
    public void verifyNegativeLoginTest(String email, String password, String expectedError) {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.navigateToSignupLogin();
        loginPage.login(email, password);
        String actualMessage = loginPage.getErrorMessage();
        softAssert.assertEquals(actualMessage, expectedError, "Error message does not match");
        softAssert.assertAll();
    }
    @Test(dataProvider = "html5ValidatiobData", retryAnalyzer = com.automation.utils.Retry.class)
    public void verifyHTML5ValidationMessageTest(String email, String password) {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.navigateToSignupLogin();
        driver.navigate().refresh();
        loginPage.login(email, password);
        //refresh the page to reset the state
        String actualMessage = loginPage.getEmailValidationMessage();
        softAssert.assertTrue(actualMessage.contains("@"), 
            "message does not contain '@' : " + actualMessage);
    }
    
}

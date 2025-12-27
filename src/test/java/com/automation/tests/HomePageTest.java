package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void shouldSearchProductFromHomePage() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isPageOpened(), "Home page did not open");

    }
}

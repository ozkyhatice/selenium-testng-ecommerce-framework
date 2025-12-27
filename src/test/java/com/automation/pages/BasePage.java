package com.automation.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    protected void open(String url) {
        driver.get(url);
    }
    protected String getTitle() {
        return driver.getTitle();
    }
}

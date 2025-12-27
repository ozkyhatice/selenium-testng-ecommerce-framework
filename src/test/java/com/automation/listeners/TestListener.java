package com.automation.listeners;

import com.automation.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testClass).getDriver();

            try {
                File screenshot =
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                File destination =
                        new File("target/screenshots/" + result.getName() + ".png");

                destination.getParentFile().mkdirs();
                Files.copy(screenshot.toPath(), destination.toPath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

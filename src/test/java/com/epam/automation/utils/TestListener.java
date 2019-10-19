package com.epam.automation.utils;

import com.epam.automation.driver.DriverSingleton;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private Logger logger = Logger.getLogger(TestListener.class);

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot();
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }

    @Attachment(type = "image/png")
    private byte[] saveScreenshot() {
        try {
            File screenCapture = ((TakesScreenshot) DriverSingleton
                    .getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                            + getCurrentTimeAsString() +
                            ".png"));
            logger.warn("There is a test failure. A screenshot has been saved on " + getCurrentTimeAsString());
            return Files.toByteArray(screenCapture);
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
            return null;
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

}

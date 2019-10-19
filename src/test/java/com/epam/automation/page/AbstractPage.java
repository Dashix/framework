package com.epam.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    WebDriver driver;

    protected abstract AbstractPage openPage();

    final int WAIT_TIMEOUT_SECONDS = 10;

    AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    AbstractPage() {

    }

    void highlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='10px solid yellow'", driver.findElement(locator));
    }

    void unHighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }
}

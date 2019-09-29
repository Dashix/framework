package com.epam.automation.test;

import com.epam.automation.driver.DriverSingleton;
import com.epam.automation.driver.decorator.CustomDriverDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class CustomDriverTest {

    @Test
    public void oneCanClickLoginButtonInACustomWay() {
        WebDriver driver = DriverSingleton.getDriver();
        driver = new CustomDriverDecorator(driver);
        driver.navigate().to("https://stackoverflow.com");
        driver.getTitle();
        driver.findElement(By.xpath("//a[text()='Log in']")).click();
        driver.quit();
    }
}

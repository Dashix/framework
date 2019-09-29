package com.epam.automation.page;

import com.epam.automation.driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MainPage extends AbstractPage {
    private static Logger logger = LogManager.getRootLogger();
    private static final String BASE_URL = "https://stackoverflow.com/";

    @FindBy(xpath = "//a[@href='/questions/ask']")
    public WebElement buttonAskQuestion;

    @FindBy(xpath = "//img[@class='-avatar js-avatar-me']")
    public WebElement userAvatarPic;

    @FindBy(xpath = "//div[@class='name']")
    public WebElement userName;

    protected final By userAvatarPicLocator = By.xpath("//img[@class='-avatar js-avatar-me']");
    protected final By buttonAskQuestionLocator = By.xpath("//a[@href='/questions/ask']");

    MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public AskQuestionPage openAskNewQuestionPage() {
        waitForAskQuestionButtonPresence();
        buttonAskQuestion.click();
        return new AskQuestionPage(driver);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public String getLoggedInUserName() {
        waitForUserAvatarPicPresence();
        userAvatarPic.click();
        return userName.getText();
    }

    private void waitForUserAvatarPicPresence() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(userAvatarPicLocator));
    }

    private void waitForAskQuestionButtonPresence() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(buttonAskQuestionLocator));
    }

    public static void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                            + getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private static String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}

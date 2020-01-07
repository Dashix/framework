package com.epam.automation.page;

import com.epam.automation.driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage extends AbstractPage {
    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);
    private static final String BASE_URL = "https://stackoverflow.com/";

    @FindBy(xpath = "//a[@href='/questions/ask']")
    private WebElement buttonAskQuestion;

    @FindBy(xpath = "//img[@class='-avatar js-avatar-me']")
    private WebElement userAvatarPic;

    @FindBy(xpath = "//div[@class='name']")
    private WebElement userName;

    private final By userAvatarPicLocator = By.xpath("//img[@class='-avatar js-avatar-me']");
    private final By buttonAskQuestionLocator = By.xpath("//a[@href='/questions/ask']");

    MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public MainPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    public AskQuestionPage openAskNewQuestionPage() {
        waitForAskQuestionButtonPresence();
        highlightElement(buttonAskQuestionLocator);
        logger.debug("Ask Question button highlighted");
        buttonAskQuestion.click();
        unHighlightElement(buttonAskQuestionLocator);
        logger.debug("Ask Question button unhighlighted");
        return new AskQuestionPage(driver);
    }

    @Override
    public MainPage openPage() {
        logger.info("Going to: " + BASE_URL);
        driver.navigate().to(BASE_URL);
        return this;
    }

    public String getLoggedInUserName() {
        waitForUserAvatarPicPresence();
        highlightElement(userAvatarPicLocator);
        logger.debug("Avatar pic highlighted");
        userAvatarPic.click();
        unHighlightElement(userAvatarPicLocator);
        logger.debug("Avatar pic unhighlighted");
        return userName.getText();
    }

    private void waitForUserAvatarPicPresence() {
        logger.warn("Timeout might be extended");
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(userAvatarPicLocator));
    }

    private void waitForAskQuestionButtonPresence() {
        logger.warn("Timeout might be extended");
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(buttonAskQuestionLocator));
    }

}

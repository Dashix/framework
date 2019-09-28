package com.epam.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {
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
}

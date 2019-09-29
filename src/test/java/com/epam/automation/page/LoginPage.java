package com.epam.automation.page;

import com.epam.automation.driver.DriverSingleton;
import com.epam.automation.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private static final String PAGE_URL = "https://stackoverflow.com/users/login";

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(xpath = "//*[@type='password']")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//p[@class='grid--cell s-input-message js-error-message']")
    private WebElement invalidPasswordErrorMessage;

    private final By invalidPasswordErrorMessageLocator = By.xpath("//p[@class='grid--cell s-input-message js-error-message']");

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("Stackoverflow login page opened");
        return this;
    }

    public MainPage login(User user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        logger.info("Login performed");
        return new MainPage(driver);
    }

    public MainPage login(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        buttonSubmit.click();
        return new MainPage();
    }

    public LoginPage passInvalidLoginCredentials(User user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(invalidPasswordErrorMessageLocator));
        return this;
    }

    public LoginPage passInvalidLoginCredentials(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        buttonSubmit.click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(invalidPasswordErrorMessageLocator));
        return this;
    }

    public String getPasswordValidationError() {
        return invalidPasswordErrorMessage.getText();
    }

}

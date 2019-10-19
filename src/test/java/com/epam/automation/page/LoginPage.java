package com.epam.automation.page;

import com.epam.automation.driver.DriverSingleton;
import com.epam.automation.model.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {
    private final Logger logger = Logger.getLogger(LoginPage.class);
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
    private final By inputEmailLocator = By.xpath("//*[@id='email']");
    private final By inputPasswordLocator = By.xpath("//*[@type='password']");
    private final By buttonSubmitLocator = By.xpath("//*[@id='submit-button']");

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
        logger.info("Going to: " + PAGE_URL);
        driver.navigate().to(PAGE_URL);
        logger.info("Stackoverflow login page opened");
        return this;
    }

    public MainPage login(User user) {
        highlightElement(inputEmailLocator);
        logger.debug("Input email highlighted");
        inputEmail.sendKeys(user.getEmail());
        unHighlightElement(inputEmailLocator);
        logger.debug("Input email unhighlighted");

        highlightElement(inputPasswordLocator);
        logger.debug("Input password highlighted");
        inputPassword.sendKeys(user.getPassword());
        unHighlightElement(inputPasswordLocator);
        logger.debug("Input password unhighlighted");

        highlightElement(buttonSubmitLocator);
        logger.debug("Submit button highlighted");
        buttonSubmit.click();
        unHighlightElement(buttonSubmitLocator);
        logger.debug("Submit button unhighlighted");
        logger.info("Logged in as: " + user.getUsername());
        return new MainPage(driver);
    }

    public MainPage login(String email, String password) {
        highlightElement(inputEmailLocator);
        logger.debug("Input email highlighted");
        inputEmail.sendKeys(email);
        unHighlightElement(inputEmailLocator);
        logger.debug("Input email unhighlighted");

        highlightElement(inputPasswordLocator);
        logger.debug("Input password highlighted");
        inputPassword.sendKeys(password);
        unHighlightElement(inputPasswordLocator);
        logger.debug("Input password unhighlighted");

        highlightElement(buttonSubmitLocator);
        logger.debug("Submit button highlighted");
        buttonSubmit.click();
        unHighlightElement(buttonSubmitLocator);
        logger.debug("Submit button unhighlighted");
        logger.info("Logged in as: " + email);
        return new MainPage();
    }

    public LoginPage passInvalidLoginCredentials(User user) {
        highlightElement(inputEmailLocator);
        logger.debug("Input email highlighted");
        inputEmail.sendKeys(user.getEmail());
        unHighlightElement(inputEmailLocator);
        logger.debug("Input email unhighlighted");

        highlightElement(inputPasswordLocator);
        logger.debug("Input password highlighted");
        inputPassword.sendKeys(user.getPassword());
        unHighlightElement(inputPasswordLocator);
        logger.debug("Input password unhighlighted");

        highlightElement(buttonSubmitLocator);
        logger.debug("Submit button highlighted");
        buttonSubmit.click();
        logger.warn("Timeout might be extended");
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(invalidPasswordErrorMessageLocator));
        unHighlightElement(buttonSubmitLocator);
        logger.debug("Submit button unhighlighted");
        return this;
    }

    public LoginPage passInvalidLoginCredentials(String email, String password) {
        highlightElement(inputEmailLocator);
        logger.debug("Input email highlighted");
        inputEmail.sendKeys(email);
        unHighlightElement(inputEmailLocator);
        logger.debug("Input email unhighlighted");

        highlightElement(inputPasswordLocator);
        logger.debug("Input password highlighted");
        inputPassword.sendKeys(password);
        unHighlightElement(inputPasswordLocator);
        logger.debug("Input password unhighlighted");

        highlightElement(buttonSubmitLocator);
        logger.debug("Submit button highlighted");
        buttonSubmit.click();
        logger.warn("Timeout might be extended");
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(invalidPasswordErrorMessageLocator));
        unHighlightElement(buttonSubmitLocator);
        logger.debug("Submit button unhighlighted");
        return this;
    }

    public String getPasswordValidationError() {
        return invalidPasswordErrorMessage.getText();
    }

}

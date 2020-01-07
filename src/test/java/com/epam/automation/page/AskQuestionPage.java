package com.epam.automation.page;

import com.epam.automation.driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AskQuestionPage extends AbstractPage {
    private static final Logger logger = LoggerFactory.getLogger(AskQuestionPage.class);
    private static final String BASE_URL = "https://stackoverflow.com/questions/ask";

    @FindBy(xpath = "//*[@name='question-type' and @value='code']")
    private WebElement codeQuestionRadioButton;

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement buttonNext;

    @FindBy(xpath = "//a[text()='Next']")
    private WebElement linkNext;

    @FindBy(id = "tageditor-replacing-tagnames--input")
    private WebElement inputTags;

    @FindBy(xpath = "//div[@class='js-tag-editor tag-editor multi-line s-input']")
    private WebElement inputTagsValues;

    @FindBy(xpath = "//button[text()='Previous']")
    private WebElement buttonPrevious;

    @FindBy(xpath = "//a[@title='Remove tag']")
    private WebElement removeTagLink;

    private final By inputTagsLocator = By.xpath("//*[@id='tageditor-replacing-tagnames--input'");
    private final By removeTagLinkLocator = By.xpath("//a[@title='Remove tag']");
    private final By buttonPreviousLocator = By.xpath("//button[text()='Previous']");
    private final By buttonNextLocator = By.xpath("//button[text()='Next']");
    private final By linkNextLocator = By.xpath("//a[text()='Next']");
    private final By codeQuestionRadioButtonLocator = By.xpath("//*[@name='question-type' and @value='code']");

    AskQuestionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public AskQuestionPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    public String passMultipleTagsWhileAskingAQuestion(String tag1, String tag2, String tag3) {
        chooseQuestionTypeAndClickNext();
        highlightElement(inputTagsLocator);
        logger.debug("Input tags highlighted");
        inputTags.sendKeys(tag1);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.SPACE).build().perform();
        inputTags.sendKeys(tag2);
        actions.sendKeys(Keys.SPACE).build().perform();
        inputTags.sendKeys(tag3);
        actions.sendKeys(Keys.SPACE).build().perform();
        unHighlightElement(inputTagsLocator);
        logger.debug("Input tags unhighlighted");

        String tags = inputTagsValues.getText();
        highlightElement(removeTagLinkLocator);
        logger.debug("Remove tag link highlighted");
        removeTagLink.click();
        removeTagLink.click();
        removeTagLink.click();
        unHighlightElement(removeTagLinkLocator);
        logger.debug("Remove tag link unhighlighted");

        highlightElement(buttonPreviousLocator);
        logger.debug("Previous button highlighted");
        buttonPrevious.click();
        unHighlightElement(buttonPreviousLocator);
        logger.debug("Previous button unhighlighted");
        return tags;
    }

    public String passMultipleTagsWhileAskingAQuestion(String tags) {
        chooseQuestionTypeAndClickNext();
        highlightElement(inputTagsLocator);
        logger.debug("Input tags highlighted");
        inputTags.sendKeys(tags);
        unHighlightElement(inputTagsLocator);
        logger.debug("Input tags unhighlighted");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.SPACE).build().perform();
        String enteredTags = inputTagsValues.getText();

        highlightElement(removeTagLinkLocator);
        logger.debug("Remove tag link highlighted");
        removeTagLink.click();
        removeTagLink.click();
        removeTagLink.click();
        unHighlightElement(removeTagLinkLocator);
        logger.debug("Remove tag link unhighlighted");

        highlightElement(buttonPreviousLocator);
        logger.debug("Previous button highlighted");
        buttonPrevious.click();
        unHighlightElement(buttonPreviousLocator);
        logger.debug("Previous button unhighlighted");
        return enteredTags;
    }

    @Override
    public AskQuestionPage openPage() {
        logger.info("Going to: " + BASE_URL);
        driver.navigate().to(BASE_URL);
        return this;
    }

    private void chooseQuestionTypeAndClickNext() {
        highlightElement(codeQuestionRadioButtonLocator);
        logger.debug("Code Question radio button highlighted");
        codeQuestionRadioButton.click();
        unHighlightElement(codeQuestionRadioButtonLocator);
        logger.debug("Code Question radio button unhighlighted");

        highlightElement(buttonNextLocator);
        logger.debug("Next button highlighted");
        buttonNext.click();
        unHighlightElement(buttonNextLocator);
        logger.debug("Next button unhighlighted");

        highlightElement(linkNextLocator);
        logger.debug("Next link highlighted");
        linkNext.click();
        unHighlightElement(linkNextLocator);
        logger.debug("Next link unhighlighted");
    }

}

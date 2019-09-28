package com.epam.automation.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AskQuestionPage extends AbstractPage {
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

    AskQuestionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String passMultipleTagsWhileAskingAQuestion(String tag1, String tag2, String tag3) {
        codeQuestionRadioButton.click();
        buttonNext.click();
        linkNext.click();
        inputTags.sendKeys(tag1);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.SPACE).build().perform();
        inputTags.sendKeys(tag2);
        actions.sendKeys(Keys.SPACE).build().perform();
        inputTags.sendKeys(tag3);
        actions.sendKeys(Keys.SPACE).build().perform();

        String tags = inputTagsValues.getText();
        removeTagLink.click();
        removeTagLink.click();
        removeTagLink.click();
        buttonPrevious.click();
        return tags;
    }

    @Override
    public AskQuestionPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

}

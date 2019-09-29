package com.epam.automation.bdd.stepdefinitions;

import com.epam.automation.page.AskQuestionPage;
import com.epam.automation.page.LoginPage;
import com.epam.automation.page.MainPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.automation.utils.CommonUtils.EMPTY_PASSWORD;
import static com.epam.automation.utils.CommonUtils.PASSWORD_CANNOT_BE_EMPTY_ERROR;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class StackOverFlowStepDefinitions {

    @Given("I opened stackoverflow login page")
    public void iOpenedStackOverFlowLoginPage() {
        new LoginPage().openPage();
    }

    @When("^I pass invalid ([\\D+.\\D+]+) and empty password$")
    public void iPassRandomEmailAndEmptyPassword(String randomEmail) {
        new LoginPage().passInvalidLoginCredentials(randomEmail, EMPTY_PASSWORD);
    }

    @Then("^The error about invalid empty password is displayed$")
    public void theErrorAboutInvalidEmptyPasswordIsDisplayed() {
        assertThat(new LoginPage().getPasswordValidationError(), is(equalTo(PASSWORD_CANNOT_BE_EMPTY_ERROR)));
    }


    @When("^I pass valid ([\\S+.\\D+]+) and valid (\\S+)$")
    public void iPassValidEmailAndValidPassword(String email, String password) {
        new LoginPage().login(email, password);
    }

    @Then("Username is equal to (\\D+\\d?)")
    public void usernameIsEqualToValidUsername(String expectedUsername) {
        assertThat(new MainPage().getLoggedInUserName(), is(equalTo(expectedUsername)));
    }


    @And("I open 'Ask Question' page")
    public void iOpenAskQuestionPage() {
        new MainPage().openAskNewQuestionPage();
    }

    @And("I pass multiple tags (.+) while asking a question")
    public void iPassMultipleTagsWhileAskingAQuestion(String tags) {
        new AskQuestionPage().passMultipleTagsWhileAskingAQuestion(tags);
    }

    @Then("Actual tags are equal to tags entered")
    public void actualTagsAreEqualToTagsEntered() {
    }

}

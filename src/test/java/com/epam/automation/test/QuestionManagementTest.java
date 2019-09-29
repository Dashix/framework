package com.epam.automation.test;

import com.epam.automation.model.User;
import com.epam.automation.page.LoginPage;
import com.epam.automation.service.PropertyUserCreator;
import com.epam.automation.service.UserCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class QuestionManagementTest extends CommonConditions {

    @Test()
    public void oneCanEnterMultipleTagsWhileAskingAQuestion() {
        UserCreator userCreator = new PropertyUserCreator();
        User testUser = userCreator.createUser();
        String tag1 = "java";
        String tag2 = "selenium";
        String tag3 = "webdriver";
        String expectedTags = tag1 + "\n" + tag2 + "\n" + tag3;
        String enteredTags = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .openAskNewQuestionPage()
                .passMultipleTagsWhileAskingAQuestion(tag1, tag2, tag3);
        assertThat(enteredTags, is(equalTo(expectedTags)));
    }

}

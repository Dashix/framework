package com.epam.automation.test;

import com.epam.automation.model.User;
import com.epam.automation.page.LoginPage;
import com.epam.automation.service.PropertyUserCreator;
import com.epam.automation.service.RandomUserCreator;
import com.epam.automation.service.UserCreator;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserAccessTest extends CommonConditions {

    private static final String PASSWORD_CANNOT_BE_EMPTY_ERROR = "Password cannot be empty.";

    @Test
    public void oneCanLoginStackOverFlow() {
        UserCreator userCreator = new PropertyUserCreator();
        User testUser = userCreator.createUser();
        String loggedInUserName = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .getLoggedInUserName();
        assertThat(loggedInUserName, is(equalTo(testUser.getUsername())));
    }

    @Test
    public void oneCannotLoginWithInvalidCredentials() {
        UserCreator userCreator = new RandomUserCreator();
        User randomUser = userCreator.createUser();
        LoginPage loginPage = new LoginPage(driver)
                .openPage()
                .passInvalidLoginCredentials(randomUser);
        assertThat(loginPage.getPasswordValidationError(), is(equalTo(PASSWORD_CANNOT_BE_EMPTY_ERROR)));
    }
}

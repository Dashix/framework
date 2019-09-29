package com.epam.automation.test;

import com.epam.automation.model.User;
import com.epam.automation.page.LoginPage;
import com.epam.automation.service.RandomUserCreator;
import com.epam.automation.service.UserCreator;
import org.testng.annotations.Test;

import static com.epam.automation.utils.CommonUtils.PASSWORD_CANNOT_BE_EMPTY_ERROR;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserAccessTestNegative extends CommonConditions {

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

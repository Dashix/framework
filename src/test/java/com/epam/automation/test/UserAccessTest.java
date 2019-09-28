package com.epam.automation.test;

import com.epam.automation.model.User;
import com.epam.automation.page.LoginPage;
import com.epam.automation.service.UserCreatorService;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class UserAccessTest extends CommonConditions {

    @Test
    public void oneCanLoginStackOverFlow() {
        User testUser = UserCreatorService.withCredentialsFromProperty();
        String loggedInUserName = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .getLoggedInUserName();
        assertThat(loggedInUserName, is(equalTo(testUser.getUsername())));
    }
}

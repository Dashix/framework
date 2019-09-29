package com.epam.automation.service;

import com.epam.automation.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class RandomUserCreator implements UserCreator {

    private static final String USER_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String EMPTY_PASSWORD = StringUtils.EMPTY;
    private static final String USER_EMAIL = USER_NAME + "@" + RandomStringUtils.randomAlphabetic(5) + ".com";

    public User createUser() {
        return new User(USER_NAME, USER_EMAIL, EMPTY_PASSWORD);
    }
}

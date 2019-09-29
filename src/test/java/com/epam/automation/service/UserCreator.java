package com.epam.automation.service;

import com.epam.automation.model.User;

public interface UserCreator {

    String TESTDATA_USER_NAME = "testdata.user.name";
    String TESTDATA_USER_EMAIL = "testdata.user.email";
    String TESTDATA_USER_PASSWORD = "testdata.user.password";

    User createUser();

}

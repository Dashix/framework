package com.epam.automation.service;

import com.epam.automation.model.User;

public class PropertyUserCreator implements UserCreator {

    public User createUser(){
        return new User(TestDataReaderService.getTestData(TESTDATA_USER_NAME), TestDataReaderService.getTestData(TESTDATA_USER_EMAIL),
                TestDataReaderService.getTestData(TESTDATA_USER_PASSWORD));
    }
}

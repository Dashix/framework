package com.epam.automation.service;

import com.epam.automation.model.User;

public class UserCreatorService {

    private static final String TESTDATA_USER_NAME = "testdata.user.name";
    private static final String TESTDATA_USER_EMAIL = "testdata.user.email";
    private static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User withCredentialsFromProperty(){
        return new User(TestDataReaderService.getTestData(TESTDATA_USER_NAME), TestDataReaderService.getTestData(TESTDATA_USER_EMAIL),
                TestDataReaderService.getTestData(TESTDATA_USER_PASSWORD));
    }

}

package com.epam.automation.service;

import java.util.ResourceBundle;

class TestDataReaderService {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    static String getTestData(String key){
        return resourceBundle.getString(key);
    }
}

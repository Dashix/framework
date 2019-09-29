package com.epam.automation.bdd.stepdefinitions;

import com.epam.automation.driver.DriverSingleton;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ScenarioHooks {

    @Before
    public void beforeScenario(){
        DriverSingleton.getDriver();
    }

    @After
    public void afterScenario(){
        DriverSingleton.closeDriver();
    }
}

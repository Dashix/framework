This repository is created for the purpose of completion of modules 9 and 10 from Test Automation Mentoring Program related to TA frameworks and design patterns

Module 9:
1) execute these command to run tests with valid parameters (login/password):

mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml -Denvironment=dev clean test

or

mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testng-all.xml -Denvironment=dev clean test

2) changing environment parameter value to 'qa' will lead to test failures (and screenshots appearance) as user parameters are incorrect there:

mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml -Denvironment=qa clean test

Module 10:
1) Singleton pattern has been implemented as DriverSingleton during module 9
2) Factory method pattern has been implemented at service layer (UserCreator interface and specific users creation)
3) Decorator pattern has been implemented in 'decorator' package as CustomDriverDecorator, a new test CustomDriverTest has been implemented to test this behavior
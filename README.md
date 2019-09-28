This repository is created for the purpose of completion of modules 9 and 10 from Test Automation Mentoring Program related to TA frameworks and design patterns

Module 9:
1) execute these command to run tests with valid parameters (login/password):

mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml -Denvironment=dev clean test

or

mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testng-all.xml -Denvironment=dev clean test

2) changing environment parameter value to 'qa' will lead to test failures (and screenshots appearance) as user parameters are incorrect there:

mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml -Denvironment=qa clean test

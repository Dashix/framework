Feature: User access to stackoverflow.com

  Background:
    Given I opened stackoverflow login page



  Scenario: Trying to login with invalid credentials
    When I pass invalid random@email.com and empty password
    Then The error about invalid empty password is displayed




  Scenario Outline: Trying to login with valid credentials
    When I pass valid <email> and valid <password>
    Then Username is equal to <expected username>

    Examples:
      | email           | password        | expected username |
      | bubblin@mail.ru | .M*Yu2fq9*d%CqF | test              |
      | 34573@mail.ru   | SXEez6HhBbYZ4_N | test1             |




  Scenario Outline: Trying to pass multiple tags while asking a question
    When I pass valid <email> and valid <password>
    And I open 'Ask Question' page
    And I pass multiple tags java selenium webdriver while asking a question
    Then Actual tags are equal to tags entered

    Examples:
      | email           | password        |
      | 34573@mail.ru   | SXEez6HhBbYZ4_N |

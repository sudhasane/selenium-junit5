Feature: Acme Bank

  Scenario: Successful login
    Given the Acme Bank login page is displayed
    When the user enters valid login credentials
    Then the Acme Bank main page is displayed
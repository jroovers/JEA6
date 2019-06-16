@browser
Feature: Modify your profile

 Scenario: modify your profile
    Given I am logged in to the application
    And on my profile
    When choose to change my profile
    And enter some details
    And confirm my changes
    Then I expect these values to be visible on my profile
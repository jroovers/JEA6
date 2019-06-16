@browser
Feature: Create A kweet

    Scenario: Place a kweet
        Given I am logged in to the application
        When I type some text for a kweet: "This is a selenium test kweet!"
        And I confirm my kweet
        Then my kweet should be added with text "This is a selenium test kweet!"

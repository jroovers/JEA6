@browser
Feature: Profile features

    Scenario: follow or unfollow user
        Given I am logged in to the application
        And on someone else's profile
        When I choose to follow
        Then I expect myself to show up in that persons followers list

    Scenario: modify your profile
        Given I am logged in to the application
        And on my profile
        When choose to change my profile
        And enter some details
        And confirm my changes
        Then I expect these values to be visible on my profile
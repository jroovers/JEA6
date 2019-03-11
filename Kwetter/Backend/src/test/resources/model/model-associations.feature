Feature: Model Associations
    Let's see if the associations within the model are OK.

  Scenario: Everyone follows everyone
    Given there are 10 users
    When everyone starts following eachother
    Then everyone should have 9 followers

  Scenario: Everyone follows everyone big
    Given there are 1000 users
    When everyone starts following eachother
    Then everyone should have 999 followers

  Scenario: Everyone places some tweets
    Given there are 10 users
    When everyone places 10 tweet
    Then there should be 100 tweets in total
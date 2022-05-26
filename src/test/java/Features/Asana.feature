Feature: Asana Test

  Scenario: Get workspace details: gid
    Given I have workspace object
    When  User perform asana GET workspace operation
    Then  User is able to see response with workspace details

  Scenario: Post new project
    Given I have project object
    When  User perform asana POST project operation
    Then  User is able to see response with project details

  Scenario: Delete project
    Given User perform asana POST project operation
    When User perform asana Delete project operation
    Then User is able to see in response list of projects without deleted project details
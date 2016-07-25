Feature: AddComments

  @DeleteComments
  Scenario: Add new comment
    Given I am on the home page
    When I create a new comment
    Then I should see it in the comments list


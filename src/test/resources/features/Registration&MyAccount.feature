@userflow
Feature: Registration, Login and MyAccount

  Background: 
    Given I am on the home page
    And I follow "Sign in" link

  @regression
  Scenario Outline: Verify Login Functionality
    And I fill "email address" with "<email>"
    And I fill "password1" with "<password>"
    And I click "sign in"
    Then I should see "<heading>" heading

    Examples: 
      | email                     | password | heading        |
      | goswami.tarun77@gmail.com | test1234 | MY ACCOUNT     |
      | wrongusername             | test     | AUTHENTICATION |

  @regression
  Scenario: Create New User
    When I fill "registration email textbox" with "goswami.tarun77+1@gmail.com"
    Then I click "create an account button"
    And I enter following details
      | First Name | Tarun    |
      | Last Name  | Goswami  |
      | Password   | Test1234 |
      | Date       |       13 |
      | Year       |     1989 |
    And I click "register button"

  @regression @api
  Scenario: Verify user details
    #Given When I request "ah.programsbuzz.com/users"
    Then I gets following response
      | Username | Email                     |
      | tgoswami | goswami.tarun77@gmail.com |
      | mahuja   | manish.ahuja@gmail.com    |
      | ssharma  | sunil.sharma@gmail.com    |

#Author: Tarun Goswami
#Website: ProgramsBuzz
@contactus
Feature: As an Ecommerce store owner,
  I want customer are able to contact me in case of any query

  Background: 
    Given I am on the home page
    And I follow "Contact us" link

  # Doc String Example
  @regression
  Scenario: Fill in Contact Us form
    When I fill "email address" with "goswami.tarun77"
    And I fill in "message" with:
      """
      Dear,
      
      Its been more than a week, I have not received my order.
      
      Thanks,
      Tarun Goswami
      """

  # Combining Scenario Outline and Doc String
  @regression @testdoc
  Scenario Outline: Fill in Email form
    When I fill "email address" with "<email>"
    And I fill in "message" with:
      """
      Dear <user>,
      
      Thank you for applying for Job of <post>, we will get back to you soon.
      
      Thanks,
      Team HR
      """

    Examples: 
      | email                     | user          | post    |
      | goswami.tarun77@gmail.com | Tarun Goswami | QA      |
      | manish.kumar@gmail.com    | Manish Kumar  | IT Head |
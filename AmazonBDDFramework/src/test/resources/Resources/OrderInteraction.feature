#OrderInteraction
@Orders
Feature: Validating Order functionality

  Background: Pre-requisites of Order Functionality
    Given Website is up and running
    And User can click on SignIn Option under Accounts and Lists Widget
    When User Enter "kovidmehta10@gmail.com" and "newhoneybees@93" as credentials
    Then Validate User Login
    

  Scenario: User can check if there are any open orders
    Given User is on Amazon Home Page as a Logged in User
    When User clicks on Order widget
    Then User should see Open order details if any open orders are present
    

  Scenario: User can check the details of the past order older than 6 months old
    Given User is on Amazon Home Page as a Logged in User
    When User clicks on Order widget
    Then User should be able to see order from entered year
    | 2018 |

#AmazonPayFunctionality
@AmazonPay
Feature: Validating Amazon Pay Features
	

  Background: Pre-requisites of Amazon Pay Functionality
    Given Website is up and running
    And User can click on SignIn Option under Accounts and Lists Widget
    When User Enter "kovidmehta10@gmail.com" and "honeybees@93" as credentials
    Then Validate User Login

  Scenario: Validate post login user can check Amazon Balance
    Given User is on Amazon Home Page as a Logged in User
    When User go to Accounts under Accounts and List widget and Click Amazon Pay
    Then User should be able to see current Amazon Pay Balance

  Scenario: Validate user can view Payment Methods and Amount Options while adding Amazon Balance
    Given User is on Amazon Home Page as a Logged in User
    When User go to Accounts under Accounts and List widget and Click Amazon Pay
    Then User should be able to see different payment methods and Amount Options to add Amazon Balance

  Scenario: Validate user can view Amazon Pay Statement
    Given User is on Amazon Home Page as a Logged in User
    When User go to Accounts under Accounts and List widget and Click Amazon Pay
    And Clicks on Manage Amazon Pay Balance to view Statement
    Then Amazon Transactions should be displayed to the user

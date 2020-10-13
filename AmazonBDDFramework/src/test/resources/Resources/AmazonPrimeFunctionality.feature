#AmazonPrimeFunctionality
@AmazonPrime
Feature: Validating Amazon Prime Feature

   Background: Pre-requisites of Amazon Pay Functionality
		Then Validate User Login

  Scenario: Validate User is able to Check the expiry date of Prime Membership
    Given User is on Amazon Home Page as a Logged in User
    When User go to Accounts under Accounts and List Widget and Click Prime
    Then User should be able to see the expiry date of Prime Membership

  Scenario: Validate User can go through the products under Prime offers
    Given User is on Amazon Home Page as a Logged in User
    When User go to Accounts under Accounts and List Widget and Click Prime
    Then User should be able to click on Lightning Deals to see the deals

  Scenario: Validate that users can go to watch Prime videos
    Given User is on Amazon Home Page as a Logged in User
    When User go to Accounts under Accounts and List Widget and Click Prime
    Then User should be able to click on PrimeVideos
#CheckoutFeatures
@Checkout @SSO
Feature: Validating Checkout Features
  
  Background: Pre-requisites of Amazon Pay Functionality
		Then Validate User Login

  Scenario: User is able to Choose Delivery Option when purchasing a product
    Given User is on Amazon Home Page as a Logged in User
    And User selects any product from the Home Page
    And Redirects to New tab opened
    When User clicks on Buy Now on Product page
    Then User should be able to see all the available Delivery options


  Scenario: User is able to identify the different payment options available
    Given User is on Amazon Home Page as a Logged in User
    And User selects any product from the Home Page
    And Redirects to New tab opened
    When User clicks on Buy Now on Product page
    And Selects Anyone Delivery Option
    Then User should be able to see all the payment methods
#HomePageValidation
@HomePage @SSO
Feature: Home Page Validations

  Background: Pre-requisites of Amazon Pay Functionality
		Then Validate User Login
	

  Scenario: User can check the ticker deals from the home page
    Given User is on Amazon Home Page as a Logged in User
    Then User should be able to see Ticker Deals and Capture Screenshot


  Scenario: Validate that user can scroll through all the items displayed in Today's deal
    Given User is on Amazon Home Page as a Logged in User
    When User searches for Today Deals Section
    Then User should be able to fetch details of item using scroller
    | 5 |

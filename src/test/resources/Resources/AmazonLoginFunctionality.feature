@Login
Feature: Validating Amazon Login Functionality

  Background: Pre-requisites of Login Functionality
    Given Website is up and running


  Scenario Outline: Validate Login fails with invalid credentials
    Given User can click on SignIn Option under Accounts and Lists Widget
    When User Enter "<Username>" and "<Password>" as credentials
    Then User should see Error Message stating Login Failed
    | There was a problem |

    Examples: 
      | Username     |  | Password     |
      | YourUsername |  | incorrectPassword |

  Scenario Outline: Validate User is able to log out post successful login
    Given User can click on SignIn Option under Accounts and Lists Widget
    When User Enter "<Username>" and "<Password>" as credentials
    Then Validate User Login
    And Click on Log Out to redirect on Sign In Page

    Examples: 
      | Username     |  | Password     |
      | YourUsername |  | YourPassword |

  Scenario Outline: Validate User can log in with valid credentials
    Given User can click on SignIn Option under Accounts and Lists Widget
    When User Enter "<Username>" and "<Password>" as credentials
    Then Validate User Login
    
     Examples: 
      | Username     |  | Password     |
      | YourUsername |  | yourPassword |

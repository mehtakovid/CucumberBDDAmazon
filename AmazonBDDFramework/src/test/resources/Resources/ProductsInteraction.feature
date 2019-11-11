#ProductsInteraction
@ProductsInteraction
Feature: Validating user interacting with Products on Amazon

  Background: Pre-requisites for Products interaction
    Given Website is up and running
    And User can click on SignIn Option under Accounts and Lists Widget
    When User Enter "kovidmehta10@gmail.com" and "newhoneybees@93" as credentials
    Then Validate User Login
    

  Scenario: User search a Product on Amazon and Add the product in the Wishlist
    Given User is on Amazon Home Page as a Logged in User
    When User enters the Product Name in the Search bar and presses Enter
    | Weightlifting Belt |
    Then User should be able to list of Products containing Product Name
    | Weightlifting Belt |
    

  Scenario: User selects one Item from Today Deals and Add it to Cart.
    Given User is on Amazon Home Page as a Logged in User
    When User searches for Today Deals Section
    Then Select Item from Today's Deal
    #Given Redirects to New tab opened
    #Then Add the item to Cart
    

  Scenario: User is able to increase the Quantity of already added item in the cart
    Given User is on My Cart Page
    When User is able to see items added in the Cart
    Then User should be able to increase the item quantity in cart
    

  Scenario: User is able to decrese the Quantity of already added item in the cart
    Given User is on My Cart Page
    When User is able to see items added in the Cart
    Then User should be able to decrease the item quantity in cart
    

  Scenario: User can navigate to desired product through Shop By Category feature.
    Given User is on Amazon Home Page as a Logged in User
    When User searches for Today Deals Section
    Then Select Item from Today's Deal
    Given Redirects to New tab opened
    When User clicks on Ship By Category and Navigate to particular product
    Then User should be able to see the Product List


  Scenario: Validate that user can delete all the items added in the cart
    Given User is on My Cart Page
    When User is able to see items added in the Cart
    Then User should be able to delete all the items in the cart
    

  Scenario: User can add a product from wishlist into the cart and throw error if there are no products in the wishlist
    Given User is on Wishlist Page
    When User clicks on Add to Cart on Wishlist page
    Then Wishlist Item is added to cart
    

  Scenario: User can create a new wishlist
    Given User is on Wishlist Page
    When User clicks on Create a List and Enter a List Name
    | New List |
    Then User can see the new Shopping Wishlist
    | New List |
    
	
  Scenario: User can delete a Wishlist
    Given User is on Wishlist Page
    When User clicks on Manage List and Clicks on Delete List
    Then User should see popup seeking confirmation

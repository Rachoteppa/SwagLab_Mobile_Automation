
Feature: Test SwagLabs app product purchase functionality

Background:
    Given launch swaglabs app

Scenario: Validate Sauce Labs Backpack product checkout functionality
    When login as public_user
    And add random product
    Then verify checkout result page

Scenario: Validate Sauce Labs Bike Light product checkout functionality
    When login as public_user
    When add product "Sauce Labs Bike Light" to cart
    Then verify checkout result page

Scenario: Validate Sauce Labs Bike Light product remove functionality
    When login as public_user
    When add product "Sauce Labs Bike Light" to cart
    And remove product from cart
    Then verify cart is empty
	

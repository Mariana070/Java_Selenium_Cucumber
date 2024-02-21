@regression
Feature: Testing Item Purchase Functionality of the SauceDemo

  Scenario Outline: Validating Order Functionality is working for an items.(Happy Path)
    Given User provide username and password to login successfully
    When User choose the '<productName>', click  add to cart button and validate it is added
    And User click cart icon and checkout button
    And User provides '<firstName>','<lastName>','<zipCode>' to checkout page and click continue button
    Then User validate the '<productName>','<itemTotal>','<tax>','<totalPrice>' with '8'% tax rate
    And User click Finish Button and validate 'Thank you for your order!' for purchase
    Examples:
      | productName                       | firstName | lastName | zipCode | itemTotal | tax  | totalPrice |
      | Sauce Labs Backpack               | Omar      | Dep      | 60134   | 29.99     | 2.40 | 32.39      |
      | Sauce Labs Fleece Jacket          | Ivan      | Boyko    | 5968    | 49.99     | 4.00 | 53.99      |
      | Test.allTheThings() T-Shirt (Red) | LILI      | Igor     | 5968    | 15.99     | 1.28 | 17.27      |
      | Sauce Labs Bolt T-Shirt           | Alla      | Bill     | 5968    | 15.99     | 1.28 | 17.27      |

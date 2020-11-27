@VendingMachine @Regression
Feature: Vending Machine Testing

  Scenario Outline: Verify Vending machine is accepting all types of coins
    Given I insert the coin value of <ValueOf> Cent
    Then vending machine is accepting the <ValueOf> Cent coin

    Examples:
      | ValueOf |
      | PENNY   |
      | NICKLE  |
      | DIME    |
      | QUARTER |

  Scenario Outline: Verify user is allowed to select different products from the list
    Given I insert the coin value of <ValueOf> Cent
    Then I am able to select <ProductType> product from the list

    Examples:
      | ValueOf                                           | ProductType |
      | QUARTER                                           | COKE        |
      | QUARTER,DIME                                      | PEPSI       |
      | QUARTER,DIME,NICKLE,PENNY,PENNY,PENNY,PENNY,PENNY | SODA        |

  Scenario: Verify user is allowed to take refund by cancelling the request
    Given I insert the coin value of QUARTER Cent
    Then I can get refund amount QUARTER by cancelling the request

  Scenario Outline: Verify selected product is returned with remaining change if any
    Given I insert the coin value of <ValueOf> Cent
    When I am able to select <ProductType> product from the list
    Then I got remaining <BalanceAmount> balance back

    Examples:
      | ValueOf           | ProductType | BalanceAmount |
      | QUARTER,NICKLE    | COKE        | 5             |
      | QUARTER,DIME,DIME | PEPSI       | 10            |
      | QUARTER,DIME,DIME | SODA        | 0             |


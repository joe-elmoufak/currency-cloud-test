Feature: Get a quote for buying or selling two unique currencies

  Background: Retrieve Authentication Token
    Given I login to Currency Cloud

  Scenario: A user is able to get a quote to sell GBP for USD and then logout
    Given I get a quote to "sell" "1150.00" of "GBP" for "USD"
    When I verify that the rates are converted correctly
    Then  I logout of Currency Cloud and close the session

  Scenario: Verify that a user needs an auth token in order to get a quote
    Given An unauthorised user attempts to get a conversion quote
    Then I verify that the quote fails with a "unauthorised" error

  Scenario Outline: Verify that a user must enter valid parameters in order to retrieve a quote
    Given I get a quote to "<fixed_side>" "<amount>" of "<currency1>" for "<currency2>"
    Then I verify that the quote fails with a "<expected_outcome>" error

    Examples:
    |fixed_side|amount  |currency1|currency2|expected_outcome|
    |sell      |1150.0.0|GBP      |USD      |bad_request     |
    |sell      |1150.0  |GBP      |GBP      |bad_request     |
Feature: Asia insurance - Product distribution website

  @form
  Scenario Outline: Access to page

    Given user navigates to "https://imedical.asiainsurance.hk/" page
    When user selects age <age>
     And user selects gender <gender>
     And user clicks on "Show Results" button
    Then user is directed to the "plan" page

    Examples:
    |age|gender     |
    |0  | "Female"  |
  #  |80 | "Female"  |
  #  |0  | "Male"    |
  #  |80 | "Male"    |


    @card_details
    Scenario: Card content validation

      Given user navigates to "https://imedical.asiainsurance.hk/" page
      And user selects age 34
      And user selects gender "Male"
      And user clicks on "Show Results" button
      When user is directed to the "plan" page
      Then user see cards element
      And the card will contain the product information
        | Title           |
        | Key             |
        | Amount          |
        | Price switch    |
        | Buy button      |
        | Annual limit    |
        | Benefits link   |
        | Documents link  |

  @card_details @lang
  Scenario: Change language
    Given user navigates to "https://imedical.asiainsurance.hk/" page
    And user selects age 34
    And user selects gender "Male"
    And user clicks on "Show Results" button
    When user is directed to the "plan" page
    And user changes lang
    Then user see cards element
    And the card will contain the product information
      | Title           |
      | Key             |
      | Amount          |
      | Price switch    |
      | Buy button      |
      | Annual limit    |
      | Benefits link   |
      | Documents link  |
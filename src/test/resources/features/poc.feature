Feature: Asia insurance - Product distribution website

  @form
  Scenario Outline: Access to page

    Given user navigates to "https://imedical.asiainsurance.hk/" page
    When user selects age <age>
     And user selects gender <gender>
     And user clicks on "show_results" button
    Then user is directed to the "plan" page

    Examples:
    |age|gender     |
    |0  | "female"  |
    |80 | "male"    |


    @card_details
    Scenario: Card content validation

      Given user navigates to "https://imedical.asiainsurance.hk/" page
      And user selects age 34
      And user selects gender "male"
      And user clicks on "show_results" button
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

  @important_notes
  Scenario: User can see important notes

    Given user navigates to "https://imedical.asiainsurance.hk/" page
    And user selects age 34
    And user selects gender "male"
    And user clicks on "show_results" button
    And user is directed to the "plan" page
    And user see cards element
    When user clicks on "Documents link" link
    And user clicks on "Important notes" link
    Then a modal with title "importantNotes" should be present
    And user clicks on "ok" button
    And the modal will be not present


  @card_details @lang
  Scenario: Change language
    Given user navigates to "https://imedical.asiainsurance.hk/" page
    And user selects age 34
    And user selects gender "male"
    And user clicks on "show_results" button
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
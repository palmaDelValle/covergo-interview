Feature: Asia insurance - Product distribution website - Change language

  Background:
    Given user navigates to "https://imedical.asiainsurance.hk/" page
    And user selects age 34
    And user selects gender "male"
    And user clicks on "show_results" button

  @card_details @lang
  Scenario: Card content is present in other language
    Given user is directed to the "plan" page
    When user changes lang
    Then user see cards element
    And the card will contain the product information
      | Title          |
      | Key            |
      | Amount         |
      | Price switch   |
      | Buy button     |
      | Annual limit   |
      | Benefits link  |
      | Documents link |

  @change_lang
  Scenario Outline: Card content is present in other language
    Given user is directed to the "plan" page
    When user changes to <lang> lang
    Then the page should be in <lang> language

    Examples:
      | lang    |
      | "en"    |
      | "zh-HK" |
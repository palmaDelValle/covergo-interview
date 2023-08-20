Feature: Asia insurance - Product distribution website - Card content

  Background:
    Given user navigates to "https://imedical.asiainsurance.hk/" page
    And user selects age 34
    And user selects gender "male"
    And user clicks on "show_results" button

  @card_details
  Scenario: Card content validation
    Given user is directed to the "plan" page
    When user see cards element
    Then the card will contain the product information
      | Title          |
      | Key            |
      | Amount         |
      | Price switch   |
      | Buy button     |
      | Annual limit   |
      | Benefits link  |
      | Documents link |

  @important_notes
  Scenario: User can see important notes
    Given user is directed to the "plan" page
    And user see cards element
    When user clicks on "Documents link" link
    And user clicks on "Important notes" link
    Then a modal with title "importantNotes" should be present
    And user clicks on "ok" button
    And the modal will be not present

  @benefits_modal
  Scenario: User can see all benefits
    Given user is directed to the "plan" page
    And user see cards element
    When user clicks on "see_all_benefits" link
    Then a modal with title "benefits" should be present
    And user clicks on "ok" button
    And the modal will be not present

    # TODO: FIX
  @benefits_modal @sub-benefit
  Scenario Outline: User can see all benefits
    Given user is directed to the "plan" page
    And user see cards element
    When user clicks on "see_all_benefits" link
    And a modal with title "benefits" should be present
    And user clicks on <link> link
    Then the section Sub-benefits should <condition>
    And user clicks on <link> link
    Then the section Sub-benefits should <condition>

    Examples:
      |link                    | condition         |
      | "see_sub_bebefits"     | "be visible"      |
      | "hide_sub_bebefits"    | "not be visible"  |

Feature: Asia insurance - Product distribution website - Card content

  # This background is used to go to the main page of the application
  # and selects the required values to go to the plan page, where are the cards.
  Background:
    Given user navigates to "https://imedical.asiainsurance.hk/" page
    And user selects age 34
    And user selects gender "male"
    And user clicks on "show_results" button

  # This scenario validates that the sections that every card should contain are visible
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

    # This scenario validates that the important notes modal is shown.
  @important_notes
  Scenario: User can see important notes
    Given user is directed to the "plan" page
    And user see cards element
    When user clicks on "see_all_documents_and_notes" link
    And user clicks on "show_important_notes" link
    Then a modal with title "importantNotes" should be present
    And user clicks on "ok" button
    And the modal will be not present

    # This scenario validates that the benefits modal is shown.
  @benefits_modal
  Scenario: User can see all benefits
    Given user is directed to the "plan" page
    And user see cards element
    When user clicks on "see_all_benefits" link
    Then a modal with title "benefits" should be present
    And user clicks on "ok" button
    And the modal will be not present

    # This scenario validates that the benefits modal is shown and the content can be hide.
  @benefits_modal @sub-benefit
  Scenario: User can see all benefits and sub-benefits
    Given user is directed to the "plan" page
    And user see cards element
    When user clicks on "see_all_benefits" link
    And a modal with title "benefits" should be present
    And user clicks on "see_sub_bebefits" link
    Then the section "Sub-benefits" should "be visible"
    And user clicks on "hide_sub_bebefits" link
    Then the section "Sub-benefits" should "not be visible"
Feature: Asia insurance - Product distribution website - Card content

  Background:
    Given user navigates to "https://imedical.asiainsurance.hk/" page
    And user selects age 34
    And user selects gender "male"
    And user clicks on "show_results" button

  @payment
  Scenario: Access to Application form
    Given user is directed to the "plan" page
    When user clicks on "buy_now" button
    Then user is directed to the "application" page

    # TODO: Modify gherkin and implement steps
  @payment @policy_holder
  Scenario: User can add another policy holder
    Given user is directed to the "plan" page
    And user see cards element
    When user clicks on "buy_now" button
    Then a form with title "Personal Information of Other Policy Holder 1" should be visible

  @payment @mandatory_fields
  Scenario: Mandatory fields should be complimented
    Given user is directed to the "plan" page
    And user see cards element
    And user clicks on "buy_now" button
    Then a form with title "Personal Information of Other Policy Holder 1" should be visible
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
    Then a form with title "application_form" should be visible


  @payment @policy_holder
  Scenario: User can add another policy holder
    Given user is directed to the "plan" page
    And user see cards element
    When user clicks on "buy_now" button
    Then a form with title "proposed_insured_and_holder_details" should be visible
    And user clicks on "add_another_policy_holder" button
    Then a form with title "other_policy_holder_details" should be visible

  @payment @mandatory_fields
  Scenario: Mandatory fields should be complimented
    Given user is directed to the "plan" page
    And user see cards element
    And user clicks on "buy_now" button
    And a form with title "proposed_insured_and_holder_details" should be visible
    When user fulfill mandatory fields
      | field                          | type   | value                  |
      | salutation                     | select | mr                     |
      | full_name                      | input  | Javier Palma           |
      | hkid_number                    | input  | A1234563               |
      | day                            | select | 03                     |
      | month                          | select | 08                     |
      | year                           | select | 1991                   |
      | male                           | div    |                        |
      | height                         | input  | 178                    |
      | weight                         | input  | 85                     |
      | occupation                     | select | 001                    |
      | other                          | div    |                        |
      | place_of_residence             | input  | Spain                  |
      | correspondence_address         | input  | Imagine                |
      | personal_email_address         | input  | personal_mail@mail.com |
      | contact_telephone_no           | input  | 12345678               |
      | claim_settlement_mode          | select | cheque                 |
      | declaration                    | button |                        |
      | commision_disclosure_statement | button |                        |
      | cancellation                   | button |                        |
      | confirm_switch                 | switch |                        |
    And user clicks on "continue" button
    Then a success modal should be visible
    When user clicks on "confirm" button
    Then user is directed to the "summary" page

  @payment @mandatory_fields @validation
  Scenario: Error messages for mandatory fields should be displayed
    Given user is directed to the "plan" page
    And user see cards element
    And user clicks on "buy_now" button
    And a form with title "proposed_insured_and_holder_details" should be visible
    When user clicks on "continue" button
    Then field should has field is required message
      | field                          | type   |
      | salutation                     | select |
      | full_name                      | input  |
      | hkid_number                    | input  |
      | day                            | select |
      | month                          | select |
      | year                           | select |
      | height                         | input  |
      | weight                         | input  |
      | occupation                     | select |
      | other                          | div    |
      | correspondence_address         | input  |
      | personal_email_address         | input  |
      | contact_telephone_no           | input  |
      | claim_settlement_mode          | select |
      | declaration                    | button |
      | commision_disclosure_statement | button |
      | cancellation                   | button |
      | confirm_switch                 | switch |
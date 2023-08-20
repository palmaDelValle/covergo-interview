Feature: Asia insurance - Product distribution website - Form

  @form
  Scenario Outline: Access to page

    Given user navigates to "https://imedical.asiainsurance.hk/" page
    When user selects age <age>
    And user selects gender <gender>
    And user clicks on "show_results" button
    Then user is directed to the "plan" page

    Examples:
      | age | gender   |
      | 0   | "female" |
      | 80  | "male"   |

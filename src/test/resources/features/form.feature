Feature: Asia insurance - Product distribution website - Form

  # This scenario validates that the user can manage the selects field of the form
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
      | 0   | "male"   |
      | 80  | "female" |

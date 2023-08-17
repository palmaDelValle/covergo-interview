Feature: Asia insurance - Product distribution website

  @chrome @firefox
  Scenario Outline: Access to page

    Given user navigates to "https://imedical.asiainsurance.hk/" page
    When user selects age <age>
     And user selects gender <gender>
     And user clicks on "Show Results" button
    Then user is directed to the "plan" page

    Examples:
    |age|gender     |
    |0  | "Female"  |
    |80 | "Female"  |
    |0  | "Male"    |
    |80 | "Male"    |
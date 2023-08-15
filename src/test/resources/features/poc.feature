Feature: Poc

  @chrome @firefox
  Scenario: Navigate

    Given user navigates to "https://covergo.com/" page
    When user clicks on link "join-us"
    Then user is directed to the "Working at CoverGo" page
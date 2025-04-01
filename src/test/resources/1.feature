Feature: Flipkart and Amazon

  @flipkart
  Scenario: Flipkart website
    Given I open Flipkart flight booking page
    And the page title is verified
    And the page URL and navigate pages are verified


  @amazon
  Scenario: Amazon website
    Given I open Amazon home page
    And the static dropdowns are verified
    And few Actions are performed and verified
    And Scroll vertically downwards verified
    And footer section links are verified
    And footer section broken links are verified
    Then i closed the browser
    #And Alert popups are verified
    #And Window Handles are verified
    #And Frames are verified

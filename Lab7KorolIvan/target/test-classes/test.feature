
Feature: Rozetka
  Scenario: S1
    Given price min '100'
    And price max '200'
    And producer 'Cinzano'
    When when I choose prise
    And choose producer
    And choose good
    Then Button sould be displayed


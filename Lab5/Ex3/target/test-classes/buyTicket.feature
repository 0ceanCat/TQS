Feature: Buy an air ticket in Blazedemo

  Scenario: Buy a ticket from Philadelphia to Rome
    When I navigate to "https://blazedemo.com//"
    And I choose departure city 'Philadelphia'
    And I choose destination city 'Rome'
    And I click in Find Flights
    And I choose the '1'th flight
    And I fill the form with name 'ABC', address 'ERG', state 'BCD', city 'SDF', zipCode '12345', creditCardNumber '123123123', nameOnCard '1'
    And I click in Purchase Flight
    Then I should have a page with title 'BlazeDemo Confirmation'
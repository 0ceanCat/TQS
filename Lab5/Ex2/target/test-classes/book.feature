Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.
  Background: A library
    Given a book with the title 'One good book' and the category 'IT', written by 'Anonymous', published in 2013-03-14
    And another book with the title 'Some other book' and the category 'Action', written by 'Tim Tomson', published in 2014-08-23
    And another book with the title 'How to cook a dino' and the category 'Fantasy', written by 'Fred Flintstone', published in 2012-01-01
    And another book with the title 'Horror book' and the category 'Horror', written by 'Fred Flintstone', published in 2012-01-01
    And another book with the title 'Another fantasy book' and the category 'Fantasy', written by 'Fred Flintstone', published in 2012-01-01

  Scenario: Search books by publication year
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
    And Book 1 should have the title 'Some other book'
    And Book 2 should have the title 'One good book'


  Scenario: Search books by category
    When the customer searches for books by category 'Fantasy'
    Then 2 books should have been found
    And Book 1 should have the title 'How to cook a dino'
    And Book 2 should have the title 'Another fantasy book'

  Scenario: Search books by title
    When the customer searches for books by title 'One good book'
    Then 1 books should have been found
    And Book 1 should have the title 'One good book'

Feature: To create new Pet

  Scenario Outline: Title of your scenario outline
    Given user is using the baseURI
    When the user makes the post call to the endpoint "/pet" with "<name>" and "<status>"
    Then user should get a response code: 200

    Examples: 
      | name | status    |
      | Dog     | available |
    


 
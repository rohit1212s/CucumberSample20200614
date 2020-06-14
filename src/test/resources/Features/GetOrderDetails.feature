Feature: Get Pet details
  
  @tag2
  Scenario Outline: Title of your scenario outline
  Given user is using the baseURI
  When user retrieves the pet details "/pet/" with "<id>"
  Then user should get a response code: 200

    Examples: 
      | id  | 
      | 74865486549595 | 
      | 74865486549596 | 
      
    Scenario Outline: Title of your scenario outline
    Given user is using the baseURI
    When the user makes the put call to the endpoint "/pet" with "<id>", "<name>" and "<status>"
    Then user should get a response code: 200

    Examples: 
      | id           		| name     | status    |
      | 74865486549595 | Dog Updated 456 | pending |
      | 74865486549596 | Cat Updated 456 | sold |
      
   
   Scenario Outline: Title of your scenario outline
    Given user is using the baseURI
    When the user makes the delete call to the endpoint "/pet/" with "<id>"
    Then user should get a response code: 200
    When user retrieves the pet details "/pet/" with "<id>"
    Then user validates the message after a pet is deleted

    Examples: 
      | id           	 |
      | 74865486549595 |
      | 74865486549596 |
      
      
 
    
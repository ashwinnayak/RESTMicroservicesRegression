Feature: Users Tests: Test all the GET requests for User data

  @getAllUserTest @all @usersTests
  Scenario: Get all User data
    When I perform GET operation to get all User data
    Then the status response code must be 200
    And the status in the body must be "OK"
    And all the User data must be returned

  @getAllUserTest @all @usersTests @recordCount
  Scenario Outline: Validate the count of records in response of All Users
    When I perform GET operation to get all User data
    Then the status response code must be 200
    And the response should have <count> number of records
    Examples:
      | count |
      | 10  |

    # Expected to FAIL because this API is not creating new records actually AND so the total no. of records doesn't increase
  @userTests @All @userPositiveTest @test
  Scenario Outline: Validate number of users increase after creating a new user
    Given I perform GET operation to get all User data
    Then the response should have <count> number of records
    When I perform post operation to create a Post
    Then the status in the body must be "Created"
    Given I perform GET operation to get all User data
    Then the response should have <increasedCount> number of records
    Examples:
      | count | increasedCount |
      | 10   |          11   |

  @userTests @schemaTest @all
  Scenario Outline: Validate Schema of a User record
    When I perform GET operation to get User data with id <id>
    Then I should get a valid schema And body parameters in User response
    Examples:
      | id  |
      | "3" |
      | "5" |

  @userTests @all
  Scenario Outline: Update A User's Details with valid data
    When I perform put operation to update User's detail with id <id>
    Then the status response code must be 200
    And the status in the body must be "OK"
    And the returned updated data must contain the input body data <id>
    Examples:
      | id   |
      | "1" |

  @userTests @all
  Scenario Outline: Execute Patch on certain params of the User's Details with valid data
    When I perform PATCH operation to update some of the User's detail with id <id>
    Then the status response code must be 200
    And the status in the body must be "OK"
    Examples:
      | id  |
      | "3" |

  @userTests @all
  Scenario Outline: Update A User's Details with Invalid ID
    When I perform put operation to update User's detail with id <id>
    Then an error status code must be returned
    Examples:
      | id   |
      | "89" |

    # This scenario is expected to FAIL as the API accepts All null params & ONLY special chars in ID & other critical params
  @userTests @all
  Scenario Outline: Update A User's Details with Invalid data
    When I perform put operation to update User's detail with invalid data and valid id <id>
    Then an error status code must be returned
    Examples:
      | id  |
      | "4" |

     # This scenario is expected to FAIL as the API accepts All null params & ONLY special chars in ID & other critical params
  @userTests @all
  Scenario Outline: Update A User's Details with All Null data
    When I perform put operation to update User's detail with All Null data but valid id <id>
    Then an error status code must be returned
    Examples:
      | id  |
      | "4" |

  @getSingleUserTest @getSingleUserPositiveTest @all @usersTests
  Scenario Outline: Get a single User data using id
    When I perform GET operation to get a single User data from id <id>
    Then the status response code must be 200
    And the status in the body must be "OK"
    And the User data must be returned
    Examples:
      | id  |
      | "1" |
      | "3" |

  @getSingleUserTest @getSingleUserNegativeTest @all @usersTests
  Scenario Outline: When id consisting of alphabets/non existing ids are passed to the get API request for single employee
    When I perform GET operation to get a single User data with invalid id <id>
    Then an error status code must be returned
    And the status in the body must be "Not Found"
    And the data returned must be null
    Examples:
      | id          |
      | "test"      |
      | "100000789" |

  @getSingleUserTest @getSingleUserNegativeTest @all @usersTests
  Scenario Outline: When special character are passed as ID in the get API request for single User
    When I perform GET operation to get a single User data from id <id>
    Then an error status code must be returned
    Examples:
      | id          |
      | "#%?"       |
      | "(*&&^*^%^" |



Feature: Posts Tests: Test all the POST/PUT requests for creating Posts

  @createPostTest @createPostPositiveTest @all @postTests
  Scenario: Create a Post using a post request
    When I perform post operation to create a Post
    Then the status response code must be 201
    And the returned post data must contain the Id
    And the status in the body must be "Created"

  @postTests @schemaTest @all
  Scenario Outline: Validate Schema of a Post record
    When I perform GET operation to get Post data by id <id>
    Then I should get a valid schema And body parameters in Post response
    Examples:
      | id  |
      | "3" |
      | "5" |

  @postTests @all @recordCount @Test
  Scenario Outline: Validate the number of records in response of Posts
    When I perform GET operation to get All Posts
    Then the status response code must be 200
    And the response should have <count> number of records
    Examples:
      | count |
      | 100  |

     # Expected to FAIL because this API is not creating new records actually AND so the total no. of records doesn't increase
  @postTests @All @postPositiveTest
  Scenario Outline: Validate number of posts increase after creating a new post
    Given I perform GET operation to get All Posts
    Then the response should have <count> number of records
    When I perform post operation to create a Post
    Then the status in the body must be "Created"
    Given I perform GET operation to get All Posts
    Then the response should have <increasedCount> number of records
    Examples:
      | count | increasedCount |
      | 100   |          101   |

    # This scenario should fail as this API accepts ONLY special char values as the userId
  @createPostTest @createPostNegativeTest @all @postTests
  Scenario: Create a Post with invalid parameters
    When I perform post operation to create a post record with invalid parameters
    Then the status response code must be 400

    # This scenario should fail as this API accepts ALL null parameters
  @createPostTest @createPostNegativeTest @all @postTests
  Scenario: Create a Post with with null parameters
    When I perform post operation to create a Post with null parameters
    Then the status response code must be 400

  @updatePostTest @updatePostPositiveTest @all @postTests
  Scenario Outline: Update A Post with valid data
    When I perform put operation to update a Post with id <id>
    Then the status response code must be 200
    And the status in the body must be "OK"
    And the returned updated data must contain the input body data <id>
    Examples:
      | id   |
      | "23" |

  @updatePostTest @updatePostNegativeTest @all @postTests
  Scenario Outline: Update a Post with invalid or null id in path parameter
    When I perform put operation to update a Post with invalid id <id>
    Then an error status code must be returned

    Examples:
      | id    |
      | "*&^" |
      | ""    |

    # This scenario is failing as the API accepts ONLY special char values as the userId
  @updatePostTest @updatePostNegativeTest @all @postTests
  Scenario Outline: Update a Post with valid id and invalid json data
    When I perform put operation to update a Post for Valid id <id> And with invalid json data
    Then an error status code must be returned
    Examples:
      | id   |
      | "21" |

    # This scenario will fail as the API accepts ALL null parameters
  @updatePostTest @updatePostNegativeTest @all @postTests
  Scenario Outline: Update a Post with valid id and null json data
    When I perform put operation to update a Post for id <id> with null parameters in json data
    Then an error status code must be returned

    Examples:
      | id   |
      | "21" |
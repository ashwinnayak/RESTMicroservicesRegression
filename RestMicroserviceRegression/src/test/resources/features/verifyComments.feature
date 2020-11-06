Feature: Comment Tests: Test Comment Endpoint

  @commentPositiveTest @all @commentTests
  Scenario: Create a Comment using a post request
    When I perform post operation to create a Comment
    Then the status response code must be 201
    And the returned post data must contain the Id
    And the status in the body must be "Created"

  @commentPositiveTest @schemaTest @all @commentTests
  Scenario Outline: Validate Schema of a Comment
    When I perform GET operation to get Comment data by id <id>
    Then I should get a valid schema And body parameters should be valid
    Examples:
      | id  |
      | "3" |
      | "5" |

  @commentPositiveTest @all @commentTests @recordCount
  Scenario Outline: Validate the number of records in response of Comments
    When I perform GET operation to get All Comments
    Then the status response code must be 200
    And the response should have <count> number of records
    Examples:
      | count |
      | 500  |

    # Expected to FAIL because this API is not creating new records actually AND so the total no. of records doesn't increase
  @commentTests @All @commentPositiveTest
    Scenario Outline: Validate number of comments increase after creating a new comment
      Given I perform GET operation to get All Comments
      Then the response should have <count> number of records
      When I perform post operation to create a Comment
      Then the status in the body must be "Created"
      Given I perform GET operation to get All Comments
      Then the response should have <increasedCount> number of records
      Examples:
        | count | increasedCount |
        | 500   |          501   |

  @commentTests @All @commentPositiveTest
  Scenario Outline: Update a comment with valid parameters
    When I perform put operation to update a Comment with id <id>
    Then the status response code must be 200
    And the status in the body must be "OK"
    And the returned updated data must contain the input body data <id>
    Examples:
      | id   |
      | "4"  |
      | "6"  |

  @deleteCommentTest @deleteCommentPositiveTest @all @commentTests
  Scenario Outline: Delete comment with id
    When I perform delete operation on a comment with id <id>
    Then the status response code must be 200
    And the status in the body must be "OK"
    Examples:
      | id  |
      | "3" |
      | "5" |

    # Some instances in this scenario should fail as this API accepts ONLY special char values as the Id
  @deleteCommentTest @deleteCommentNegativeTest @all @commentTests
  Scenario Outline: Test delete Comment with invalid/null id
    When I perform delete operation on a comment with id <id>
    Then an error status code must be returned
    Examples:
      | id     |
      | ""     |
      | "%$^#" |



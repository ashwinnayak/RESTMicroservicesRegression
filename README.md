# RESTMicroservicesRegression
REST API Test Suite
# REST Services Test Suite
#### *Author: Ashwin Kumar Nayak*
#### Automated test suite to test all the CRUD operations for the APIs of: https://jsonplaceholder.typicode.com/ using :
- Java
- rest-assured 4.3.1
- cucumber-java 6.8.0
- cucumber-core 6.8.0
- maven-cucumber-reporting 5.3.0
- maven-surefire-plugin 2.22.0

# What does it test? 
1. HTTP requests: GET, POST, PUT, PATCH and DELETE
2. Positive & negative tests
3. Schema Validation
4. Validating number of records Pre & Post creating new records
5. Complete response validation, Status, Code, Message, Headers
6. Validates response when invalid/null values used.

# Points to remember
1. Some tests are expected to fail as the API doesn't behave as per norms
2. API doesnt actually create new records or delete them.
3. Authentication is not present in this API [Basic, Preemptive, Oauth]

# How to execute the tests?
Pre-Requisites:
- Maven 
- Java 8 or above

Step 1: Clone the repository to your local.
Step 2: Import as a maven project into your choice of IDE
Step 3: On the project directory: `mvn clean install` to run all tests and install the dependencies.

# How to execute specific scenarios?
You can use tags to run tagged scenarios. Check the feature files to know the tags used.
In TestRunner.java, under @CucumberOptions settags=<@EnterTags> & Run the test.

Some Pre-defined tags in the feature files:
- `@createPostTest`- *Tests all scenarios for POST Create Post Request*
- `@createPostPositiveTest`- *Tests all positive scenarios for Post*
- `@commentPositiveTest`- *Tests all positive scenarios for Comments*
- `@all`- *Covers All Tests*
- `@postTests`- *All Post Tests*
- `@schemaTest`- *Tests schema of response*
- `@commentTests`- *All comment Tests*
- `@recordCount`- *Validates record count of response*
- `@createPostNegativeTest`- *Covers All positive Post tests*
- `@usersTests`- *Covers All User tests* 

To Execute Tests: `mvn test -DCucumber.filter.tags="@TagName"` [Reports will be generated]

To Execute Tests from IDE: Run TestRunner [Reports wont be generated in this case]

# How to view reports?

After test is executed using mvn, reports are generated in the project directory `target/cucumber-html-reports/`

Open the `overview-features.html` in any web browser to view the reports. The reports UI will let you to click on the features and see the detailed report and status of execution of each step in each scenario.

This framework can be setup in any CI/CD machine Jenkins, Bamboo etc. Pipeline variable injection to set tags.

## Hope that helped.
Thanks! Keep Testing!

## In case of any queries, drop a mail: getashwinnayak@gmail.com





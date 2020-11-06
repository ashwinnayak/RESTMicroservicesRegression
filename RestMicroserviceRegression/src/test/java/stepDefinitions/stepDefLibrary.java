package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static endpointMethods.endpointOperations.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

public class stepDefLibrary extends JSONBuilder {
    @When("I perform GET operation to get all User data")
    public void iPerformGETOperationToGetAllUserData() throws IOException {
        //api request to get all User data
        response = getAllUsersData();
    }

    @When("I perform GET operation to get Comment data")
    public void iPerformGETOperationToGetCommentData() throws IOException {
        //api request to get all User data
        response = getCommentData();
    }

    @When("I perform GET operation to get Comment data by id {string}")
    public void iPerformGETOperationToGetCommentDataById(String id)  throws IOException {
        //api request to get Comment by Id
        response = getCommentDataById(id);
    }

    @When("I perform GET operation to get All Comments")
    public void iPerformGETOperationToGetComments()  throws IOException {
        //api request to get Comment by Id
        response = getCommentData();
    }

    @When("I perform GET operation to get User data with id {string}")
    public void iPerformGETOperationToGetUserDataById(String id)  throws IOException {
        //api request to get User by Id
        response = getUserDataById(id);
    }

    @When("I perform GET operation to get Post data by id {string}")
    public void iPerformGETOperationToGetPostDataById(String id)  throws IOException {
        //api request to get Post by Id
        response = getPostDataById(id);
    }

    @When("I perform GET operation to get All Posts")
    public void iPerformGETOperationToGetAllPosts()  throws IOException {
        //api request to get Post by Id
        response = getAllPosts();
    }

    @Then("I should get a valid schema And body parameters should be valid")
    public void iShouldGetValidSchemaInResponse() throws IOException {
        // Validate response

        response.then().assertThat().body(matchesJsonSchemaInClasspath("comments.json"));
    }

    @Then("I should get a valid schema And body parameters in User response")
    public void iShouldGetValidSchemaInUserResponse() throws IOException {
        // Validate response

        response.then().assertThat().body(matchesJsonSchemaInClasspath("users.json"));
    }

    @Then("I should get a valid schema And body parameters in Post response")
    public void iShouldGetValidSchemaInPostResponse() throws IOException {
        // Validate response
        response.then().assertThat().body(matchesJsonSchemaInClasspath("postsSchema.json"));
    }

    @Then("the status response code must be {int}")
    public void theStatusResponseCodeMustBe(int statCode) {
        //assert that the status code
        //response.prettyPrint();
        response.then().statusCode(is(statCode));
    }

    @And("the response should have {int} number of records")
    public void theResponseRecordCountMustBe(int expectedCount) {
        //assert that the status code
        int actualCount = response.jsonPath().getList("id").size();
        //System.out.println("Count : "+actualCount);
        assertEquals(expectedCount,actualCount);

    }

    @And("all the User data must be returned")
    public void allTheEmployeesDataMustBeReturned() {

        //get data from response aslist
        List<String> id = response.getBody().jsonPath().getList("id");
        List<String> username = response.getBody().jsonPath().getList("username");
        List<String> name = response.getBody().jsonPath().getList("name");
        List<String> email = response.getBody().jsonPath().getList("email");

        //assert ID is not zero or null
       /* for (String i : id) {
            assertFalse(i.isEmpty());
            assertNotEquals("0", i);
        }*/

        //assert name not null
        for (String n : name) {
            assertFalse(n.isEmpty());
        }

        //assert UserName
        for (String user : username) {
            assertFalse(user.isEmpty());
        }

        //assert email is not zero & contains "@"
        for (String em : email) {
            assertFalse(em.isEmpty());
            assertTrue(em.contains("@"));
        }
    }

    @And("the status in the body must be {string}")
    public void theStatusInTheBodyMustBe(String statusMessage) {
        //assert that the body has status as success

        //System.out.println(response.getStatusLine());
        assertTrue(response.getStatusLine().contains(statusMessage) );

    }

    @When("I perform GET operation to get a single User data from id {string}")
    public void iPerformGETOperationToGetASingleUserDataFromId(String id) {
        //api request to get single employee data
        response = getUserDataById(id);
    }

    @When("I perform GET operation to get a single User data with invalid id {string}")
    public void iPerformGETOperationToGetASingleUserDataWithInvalidId(String id) {
        //api request to get single employee data
        response = getUserDataById(id);
    }

    @And("the User data must be returned")
    public void theUserDataMustBeReturned() {
        //get data from response asString
        String id = response.getBody().jsonPath().getString("id");
        String Name = response.getBody().jsonPath().getString("name");
        String username = response.getBody().jsonPath().getString("username");
        String email = response.getBody().jsonPath().getString("email");

        //assertions that id is not null or zero
        assertFalse(id.isEmpty());
        assertNotEquals("0", id);

        //assertion that name is not null
        assertFalse(Name.isEmpty());

        //assertions that username is not null or zero
        assertFalse(username.isEmpty());
        assertNotEquals("0", username);

        //assertions that email  is not null & contains @
        assertFalse(email.isEmpty());
        assertTrue(email.contains("@"));

    }


    @And("message in the body must be {string}")
    public void messageInTheBodyMustBe(String successMessage) {
        //assert the success message
        //System.out.println(response.getBody().jsonPath().getString("message"));
        assertEquals( successMessage,response.getBody().jsonPath().getString("message"));
    }

    @And("the data returned must be null")
    public void theDataReturnedMustBeNull() {
        //assert that the data returned is null
        assertTrue((response.getBody().jsonPath().getString("data") == null));
    }

    @Then("an error status code must be returned")
    public void anErrorStatusCodeMustBeReturned() {
        response.then().statusCode(anyOf(equalTo(404), equalTo(400), equalTo(301),equalTo(500)));
        //assert error code
        // assert (errorCode == 404 || errorCode == 400);
    }

    // create post
    @When("I perform post operation to create a Post")
    public void iPeformPostOperationToCreateAPost() throws IOException, ParseException {
        //post request to create posts
        response = createPost(postsJSONModifier("valid"));
    }

    // Validate response body
    @And("the returned post data must contain the Id")
    public void theReturnedPostDataMustContainId() throws IOException, ParseException {
        //assert that id is not null
        //assertFalse(response.getBody().jsonPath().getString("data.id").isEmpty());
        assertNotNull(response.getBody().jsonPath().getString("id"));
    }

    @And("the returned updated data must contain the input body data {string}")
    public void theReturnedUpdatedDataMustContainTheInputBodyData(String Id) throws IOException, ParseException {
       assertEquals(Id, response.getBody().jsonPath().getString("id"));
    }

    @When("I perform post operation to create a post record with invalid parameters")
    public void iPeformPostOperationToCreateAPostWithInvalidParams() throws IOException, ParseException {
        //post request with invalid json data to create a Post
        response = createPost(postsJSONModifier("invalid"));
    }

    @When("I perform post operation to create a Post with null parameters")
    public void iPeformPostOperationToCreateAPostWithNullParams() throws IOException, ParseException {
        //post request with null json data/values to create employee record
        response = createPost(postsJSONModifier("null"));
    }

    @When("I perform put operation to update a Post with id {string}")
    public void iPerformPutOperationToUpdateAPost(String id) throws IOException, ParseException {
        //put request with valid json data to update a Post
        response = updatePostParameters(postsJSONModifier("valid"), id);
    }

    @When("I perform put operation to update User's detail with id {string}")
    public void iPerformPutOperationToUpdateAUserDetail(String id) throws IOException, ParseException {
        //put request with valid json data to update a Post
        response = updateUserParameters(usersJSONModifier("valid", id), id);
    }

    @When("I perform PATCH operation to update some of the User's detail with id {string}")
    public void iPerformPatchOperationToUpdateAUserDetail(String id) throws IOException, ParseException {
        //patch request with valid json data to update a Post
        response = executePatchUserParameters(usersJSONModifier("valid", id), id);
    }

    @When("I perform put operation to update User's detail with invalid data and valid id {string}")
    public void iPerformPutOperationToUpdateAUserDetailWithInvalidData(String id) throws IOException, ParseException {
        //put request with valid json data to update a Post
        response = updateUserParameters(usersJSONModifier("invalid", id), id);
    }

    @When("I perform put operation to update User's detail with All Null data but valid id {string}")
    public void iPerformPutOperationToUpdateAUserDetailWithNullData(String id) throws IOException, ParseException {
        //put request with valid json data to update a Post
        response = updateUserParameters(usersJSONModifier("null", id), id);
    }

    @When("I perform put operation to update a Comment with id {string}")
    public void iPerformPutOperationToUpdateAComment(String id) throws IOException, ParseException {
        //put request with valid json data to update a Post
        response = updateCommentParameters(commentsJSONModifier("valid"), id);
    }

    @When("I perform put operation to update a Post with invalid id {string}")
    public void iPerformPutOperationToUpdateAPostForInvalidId(String id) throws IOException, ParseException {
        //put request with invalid json data to update a Post
        response = updatePostParameters(postsJSONModifier("valid"), id);
    }

    @When("I perform put operation to update a Post for Valid id {string} And with invalid json data")
    public void iPerformPutOperationToUpdateAPostWithValidIdAndInvalidParams(String id) throws IOException, ParseException {
        //put request with invalid json data to update a Post
        response = updatePostParameters(postsJSONModifier("invalid"), id);
    }

    @When("I perform put operation to update a Post for id {string} with null parameters in json data")
    public void iPerformPutOperationToUpdatePostForIdWithNullParametersInJsonData(String id) throws IOException, ParseException {
        //put request with null json data/values to update employee record
        response = updatePostParameters(postsJSONModifier("null"), id);
    }

    @When("I perform delete operation on a comment with id {string}")
    public void iPerformDeleteOperationOnACommentWithId(String id) {
        //delete request with valid id
        response = deleteComment(id);
    }

    // create post
    @When("I perform post operation to create a Comment")
    public void iPeformPostOperationToCreateAComment() throws IOException, ParseException {
        //post request to create Comment
        response = createComment(commentsJSONModifier("valid"));
    }

    @And("data in the response body must be equal to the path parameter id {string}")
    public void dataInTheResponseBodyMustBeEqualToThePathParameterId(String id) {
        //assert returned data is equal to id in the input
        assertEquals(response.getBody().jsonPath().getString("data"), id);
    }
}
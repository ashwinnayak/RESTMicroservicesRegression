package endpointMethods;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class endpointOperations {

    //GET Request to get all User data
    public static Response getUsersData() throws IOException {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().get(baseURI + endpointFactory.SingleUsers);
    }

    //GET Request to get all User data
    public static Response getAllUsersData() throws IOException {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().get(baseURI + endpointFactory.Users);
    }

    //GET Request to get Comment data
    public static Response getCommentData() throws IOException {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().get(baseURI + endpointFactory.Comments);
    }

    //GET Request to get Comment data
    public static Response getCommentDataById(String id) throws IOException {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().pathParam("id", id).get(baseURI + endpointFactory.SingleComments);
    }

    //GET Request to get a single User data by ID
    public static Response getUserDataById(String id) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().pathParam("id", id).get(baseURI + endpointFactory.SingleUsers);
    }

    //GET Request to get a single Post data by ID
    public static Response getPostDataById(String id) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().pathParam("id", id).get(baseURI + endpointFactory.SinglePost);
    }

    //GET Request to get all Posts
    public static Response getAllPosts() {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().get(baseURI + endpointFactory.Post);
    }

    //POST Request to create a single post data
    public static Response createPost(String json) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().body(json).post(baseURI + endpointFactory.Post);

    }

    //POST Request to create a single comment record
    public static Response createComment(String json) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().body(json).post(baseURI + endpointFactory.Comments);

    }

    //PUT Request to update a single User's data with identifier ID
    public static Response updateUserParameters(String json, String id) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().body(json).pathParam("id", id).put(baseURI + endpointFactory.SingleUsers);
//
    }

    //Patch Request to update a single User's data with identifier ID
    public static Response executePatchUserParameters(String json, String id) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().body(json).pathParam("id", id).patch(baseURI + endpointFactory.SingleUsers);
//
    }

    //PUT Request to update a single Post's data with identifier ID
    public static Response updatePostParameters(String json, String id) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().body(json).pathParam("id", id).put(baseURI + endpointFactory.SinglePost);
//
    }

    //PUT Request to update a single Comment's data with identifier ID
    public static Response updateCommentParameters(String json, String id) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().body(json).pathParam("id", id).put(baseURI + endpointFactory.SingleComments);
//
    }

    //DELETE Request to delete a single Comment with identifier ID
    public static Response deleteComment(String id) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().pathParam("id", id).delete(baseURI + endpointFactory.SingleComments);
    }

    //DELETE Request to delete a single User data with identifier ID
    public static Response deleteUserData(String id) {
        RequestSpecification requestSpecification = given().contentType("application/json");
        return requestSpecification.log().all().when().pathParam("id", id).delete(baseURI + endpointFactory.SingleUsers);

    }
}

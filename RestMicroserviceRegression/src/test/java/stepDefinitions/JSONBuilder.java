package stepDefinitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class JSONBuilder {

    public static Response response;
    public static String newUserID = "";
    public static String newPostID = "";
    public static String newID = "";
    public static String newName = "";
    public static String newEmail = "";
    public static String newTitle = "";
    public static String newBody = "";
    public static String newUserName="";


    public static String postsJSONModifier(String jsonParamType) throws IOException, ParseException {
        /* * postsJSONModifier(String jsonParamType)
         * jsonParamType- valid, invalid, null
         * if valid puts all valid values in json
         * if invalid puts all invalid values in json
         * if null, puts empty string values in json
         */
        JSONParser parser = new JSONParser();
        Object o = parser.parse(new FileReader(System.getProperty("user.dir") + "/src/test/resources/payloadData/posts.json"));
        JSONObject json = (JSONObject) o;

        // if valid put all valid values in json
        if (jsonParamType.equals("valid")) {
            newUserID = randomInteger(2);
            newID = randomInteger(3);
            newTitle = randomAlphabeticString(5);
            newBody = randomAlphabeticString(10);

        }
        // if invalid put all invalid values in json
        else if (jsonParamType.equals("invalid")) {
            // Setting special characters in UserID & ID
            newUserID = "&%$";
            newID = "*&^";
            newTitle = randomAlphabeticString(5);
            newBody = randomAlphabeticString(10);
        }
        // if null put all empty string in json
        else if (jsonParamType.equals("null")) {
            newUserID = "";
            newID = "";
            newTitle = "";
            newBody = "";
        }

        //puts all values to in the json
        json.put("userId", newUserID);
        json.put("id", newID);
        json.put("title", newTitle);
        json.put("body", newBody);

        //returns json as string
        return json.toJSONString();
    }

    public static String usersJSONModifier(String jsonParamType, String id) throws IOException, ParseException {
        /* * usersJSONModifier(String jsonParamType)
         * jsonParamType- valid, invalid, null
         * if valid puts all valid values in json
         * if invalid puts all invalid values in json
         * if null, puts empty string values in json
         */
        JSONParser parser = new JSONParser();
        Object o = parser.parse(new FileReader(System.getProperty("user.dir") + "/src/test/resources/payloadData/users.json"));
        JSONObject json = (JSONObject) o;

        // if valid put all valid values in json
        if (jsonParamType.equals("valid")) {
            newID = id;
            newUserName = randomAlphabeticString(5);
            newName = randomAlphabeticString(10);

        }
        // if invalid put all invalid values in json
        else if (jsonParamType.equals("invalid")) {
            // Setting special characters in UserID & ID

            newID = "*&^";
            newUserName = randomAlphabeticString(5);
            newName = randomAlphabeticString(10);
        }
        // if null put all empty string in json
        else if (jsonParamType.equals("null")) {

            newID = "";
            newUserName = "";
            newName = "";
        }

        //puts all values to in the json
        json.put("id", newID);
        json.put("username", newUserName);
        json.put("name", newName);

        //returns json as string
        return json.toJSONString();
    }

    /* * commentsJSONModifier(String jsonParamType)
     * jsonParamType- valid, invalid, null
     * if valid puts all valid values in json
     * if invalid puts all invalid values in json
     * if null, puts empty string values in json
     */
    public static String commentsJSONModifier(String jsonParamType) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object o = parser.parse(new FileReader(System.getProperty("user.dir") + "/src/test/resources/payloadData/posts.json"));
        JSONObject json = (JSONObject) o;

        // if valid put all valid values in json
        if (jsonParamType.equals("valid")) {
            newPostID = randomInteger(2);
            newID = randomInteger(3);
            newName = randomAlphabeticString(5);
            newEmail = randomAlphabeticString(5)+"@test.com";
            newBody = randomAlphabeticString(10);
        }
        // if invalid put all invalid values in json
        else if (jsonParamType.equals("invalid")) {
            // Setting special characters in UserID & ID, email without @
            newPostID = "#@$";
            newID = "@!&";
            newName = randomAlphabeticString(5);
            newEmail = randomAlphabeticString(5);
            newBody = randomAlphabeticString(10);

        }
        // if null put all empty string in json
        else if (jsonParamType.equals("null")) {
            newPostID = "";
            newID = "";
            newName = "";
            newEmail = "";
            newBody = "";
        }

        //puts all values to in the json
        json.put("postId", newPostID);
        json.put("id", newID);
        json.put("name", newName);
        json.put("email", newEmail);
        json.put("body", newBody);

        //returns json as string
        return json.toJSONString();
    }


    public static String randomAlphabeticString(int count) {
        //returns string of random alphabets
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String randomInteger(int count) {
        //returns string of random numbers
        return RandomStringUtils.randomNumeric(count);
    }

    public static String randomSplChar(int count) {
        //declare a string with all special chars
        String randomSplChar = "|[]{}~`@#%^&*";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(count);

        for (int i = 0; i < count; i++) {
            // generate a random number with special characters of variable length
            int index = (int) (randomSplChar.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(randomSplChar.charAt(index));
        }

        return sb.toString();
    }


}

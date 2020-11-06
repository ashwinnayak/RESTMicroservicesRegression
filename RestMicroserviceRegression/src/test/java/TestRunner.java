import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml", "json:target/cucumber.json" },tags="@all"
       )

public class TestRunner {
    public static Properties CONFIG;

    @BeforeClass
    public static void initialize() throws IOException {
        //read and load application.properties
        CONFIG = new Properties();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/application.properties");
        CONFIG.load(fs);

        //set the base URI
        RestAssured.baseURI = CONFIG.getProperty("baseURI");
    }

}
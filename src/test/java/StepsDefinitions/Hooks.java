package StepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import model.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hooks{
    private static Logger logger = LoggerFactory.getLogger("Hooks.class");
    private static AppProperties appProperties;

    @Before
    public void initTitle(Scenario scenario){
        appProperties = new AppProperties();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        logger.info("Start scenario: " +scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario){
        System.out.println("End scenario: " +scenario.getName() + " with status "+scenario.getStatus());
    }
}

package StepsDefinitions;

import Helpers.ResponseSpecificationHelper;
import Models.Project;
import Models.Workspace;
import TestData.ObjectsStructureData;
import Utils.JsonParser;
import Utils.RequestBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StepsDefinitionsAsana {
    private static Logger logger = LoggerFactory.getLogger("StepsDefinitionsAsana.class");
    protected Workspace workspace;
    protected Project project;
    protected Response response;
    protected RequestBuilder requestBuilder = new RequestBuilder();
    protected String projectGID;

    @Given("I have workspace object")
    public void i_have_workspace_object() {
        logger.info("<<<<<<<<<<< i_have_workspace_object");
        workspace = new JsonParser().parseWorkspaceFromJson();
        logger.info("<<<<<<<<<<< Created workspace object name is " + workspace.getName());
    }

    @Given("I have project object")
    public void i_have_project_object() {
        logger.info("<<<<<<<<<<< i_have_project_object");
        project = new JsonParser().parseProjectFromJson();
        logger.info("Project name: " + project.getName() +", Team gid: "+project.getTeam());
    }

    @Given("I have updated project object")
    public void i_have_updated_project_object() {
        logger.info("<<<<<<<<<<< i_have_updated_project_object");
        project = new JsonParser().parseUpdatedProjectFromJson();
        logger.info("Project name: " + project.getName() +", Team gid: "+project.getTeam());
    }

    @When("User perform asana GET workspace operation")
    public void user_perform_asana_GET_workspace_operation() {
        logger.info("<<<<<<<<<<< user_perform_asana_GET_workspace_operation");
        response = requestBuilder.sendGETAsanaRequest_Workspaces();
        logger.info("<<<<<<<<<<< Response: " + response.asPrettyString());
    }

    @When("User perform asana POST project operation")
    public void user_perform_asana_POST_project_operation() {
        logger.info("<<<<<<<<<<< user_perform_asana_POST_project_operation");
        response = requestBuilder.sendPOSTAsanaRequest_Project();
        projectGID = response.jsonPath().getString(ObjectsStructureData.PROJECT_GID);
        logger.info("<<<<<<<<<<< Response: " + response.asPrettyString());
    }

    @When("User perform asana PUT project operation")
    public void user_perform_asana_PUT_project_operation() {
        logger.info("user_perform_asana_PUT_project_operation");
        response = requestBuilder.sendPUTAsanaRequest_Project(projectGID);
        logger.info("<<<<<<<<<<< Response: " + response.asPrettyString());
    }

    @When("User perform asana Delete project operation")
    public void user_perform_asana_Delete_project_operation() {
        response = requestBuilder.sendDELETEAsanaRequest_Project(projectGID);
        logger.info("<<<<<<<<<<< Response: " + response.asPrettyString());
        response.then().spec(ResponseSpecificationHelper.getResponseSpecificationForDeletedProject(project));
    }

    @Then("User is able to see response with workspace details")
    public void user_is_able_to_see_response_with_workspace_details() {
        logger.info("<<<<<<<<<<< user_is_able_to_see_response_with_workspace_details");
        response.then().spec(ResponseSpecificationHelper.getResponseSpecificationForWorkspaces(workspace));
        response = null;
        logger.info("<<<<<<<<<<< Test passed");
    }

    @Then("User is able to see response with project details")
    public void user_is_able_to_see_response_with_project_details() {
        logger.info("<<<<<<<<<<< user_is_able_to_see_response_with_project_details");
        response.then().spec(ResponseSpecificationHelper.getResponseSpecificationForPostedProject(project));
        requestBuilder.sendDELETEAsanaRequest_Project(projectGID); //cleaning
        response = null;
        logger.info("<<<<<<<<<<< Test passed");
    }

    @Then("User is able to see in response list of projects without deleted project details")
    public void user_is_able_to_see_in_response_list_of_projects_without_deleted_project_details() {
        logger.info("<<<<<<<<<<< user_is_able_to_see_in_response_list_of_projects_without_deleted_project_details");
        response = requestBuilder.sendGETAsanaRequest_Projects();
        logger.info("<<<<<<<<<<< Response: " + response.asPrettyString());
        List<String> gidList = response.jsonPath().getList(ObjectsStructureData.PROJECT_GID);
        Assertions.assertFalse(gidList.contains(projectGID));
    }

    @Then("User is able to see response with updated project details")
    public void user_is_able_to_see_response_with_updated_project_details() {
        logger.info("<<<<<<<<<<< user_is_able_to_see_response_with_updated_project_details");
        response.then().spec(ResponseSpecificationHelper.getResponseSpecificationForUpdatedProject(project));
        requestBuilder.sendDELETEAsanaRequest_Project(projectGID); //cleaning
        response = null;
        logger.info("<<<<<<<<<<< Test passed");
    }
}

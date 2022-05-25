package Helpers;

import Models.Project;
import Models.Workspace;
import TestData.ObjectsStructureData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.is;

public class ResponseSpecificationHelper {
    private static Logger logger = LoggerFactory.getLogger("ResponseSpecificationHelper.class");

    public static ResponseSpecification getDefaultResponseSpecification(int statusCode){
        ResponseSpecification responseSpecification = RestAssured.expect();
        responseSpecification.statusCode(statusCode);
        responseSpecification.contentType(ContentType.JSON);
        return responseSpecification;
    }

    public static ResponseSpecification getResponseSpecificationForWorkspaces(Workspace workspace){
        ResponseSpecification responseSpecification = RestAssured.expect();
        responseSpecification
                .body(ObjectsStructureData.WORKSPACE_GID, is(workspace.getGid()))
                .body(ObjectsStructureData.WORKSPACE_NAME, is(workspace.getName()))
                .body(ObjectsStructureData.WORKSPACE_RESOURCE_TYPE, is(workspace.getResource_type()));
        return responseSpecification;
    }

    public static ResponseSpecification getResponseSpecificationForPostedProject(Project project) {
        ResponseSpecification responseSpecification = RestAssured.expect();
        responseSpecification
                .statusCode(201)
                .body(ObjectsStructureData.PROJECT_NAME, is(project.getName()))
                .body(ObjectsStructureData.PROJECT_TEAM_GID, is(project.getTeamGID()));
        return responseSpecification;
    }

    public static ResponseSpecification getResponseSpecificationForDeletedProject(Project project) {
        ResponseSpecification responseSpecification = RestAssured.expect();
        responseSpecification
                .statusCode(200);
        return responseSpecification;
    }
}

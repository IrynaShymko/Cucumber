package Helpers;

import TestData.Data;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationHelper {

    public static RequestSpecification getRequestSpecificationForWorkspaces(){
        RequestSpecification requestSpecification = RestAssured.given()
                .auth().oauth2(Data.TOKEN)
                .baseUri(Data.BASE_URI)
                .basePath(Data.BASE_PATH_WORKSPACES);
        return requestSpecification;
    }

    public static RequestSpecification getRequestSpecificationForProjects() {
        RequestSpecification requestSpecification = RestAssured.given()
                .auth().oauth2(Data.TOKEN)
                .baseUri(Data.BASE_URI)
                .basePath(Data.BASE_PATH_PROJECTS);
        return requestSpecification;
    }

    public static RequestSpecification getRequestSpecificationForRemovingProject(String projectGID) {
        RequestSpecification requestSpecification = RestAssured.given()
                .auth().oauth2(Data.TOKEN)
                .baseUri(Data.BASE_URI)
                .basePath(Data.BASE_PATH_PROJECTS + "/"+projectGID);
        return requestSpecification;
    }
}

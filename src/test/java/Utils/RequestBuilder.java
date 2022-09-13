package Utils;

import Helpers.RequestSpecificationHelper;
import TestData.Data;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RequestBuilder {
    public Response sendGETAsanaRequest_Workspaces(){
       return given(RequestSpecificationHelper.getRequestSpecificationForWorkspaces())
                .when().get();
    }

    public Response sendPOSTAsanaRequest_Project() {
        return given(RequestSpecificationHelper.getRequestSpecificationForProjects())
                .when().body(new File(Data.PATH_TO_PROJECT_OBJECT)).post();
    }

    public Response sendDELETEAsanaRequest_Project(String projectGID) {
        return given(RequestSpecificationHelper.getRequestSpecificationForRemovingProject(projectGID))
                .when().delete();
    }

    public Response sendGETAsanaRequest_Projects() {
        return given(RequestSpecificationHelper.getRequestSpecificationForProjects())
                .when().get();
    }

    public Response sendPUTAsanaRequest_Project(String projectGID) {
        return given(RequestSpecificationHelper.getRequestSpecificationForUpdateProject(projectGID))
                .when().body(new File(Data.PATH_TO_PROJECT_OBJECT_UPDATE)).put();
    }
}

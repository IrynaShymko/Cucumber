package Utils;

import Models.Project;
import Models.Workspace;
import TestData.Data;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    ObjectMapper mapper;
    Project project;

    public Workspace parseWorkspaceFromJson() {
        Workspace workspace = null;
        try {

            mapper = new ObjectMapper();
            workspace = mapper.readValue(new File(Data.PATH_TO_WORKSPACE_OBJECT), Workspace.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workspace;
    }

    public Project parseProjectFromJson() {
        try {
            mapper = new ObjectMapper();
            project = mapper.readValue(new File(Data.PATH_TO_BASIC_PROJECT_OBJECT), Project.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return project;
    }

    public Project parseUpdatedProjectFromJson() {
        try {
            mapper = new ObjectMapper();
            project = mapper.readValue(new File(Data.PATH_TO_BASIC_PROJECT_OBJECT_UPDATE), Project.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return project;
    }
}

package Models;

import lombok.Data;

@Data
public class Workspace {
    private String gid;
    private String resource_type;
    private String name;

    public Workspace(String gid, String resource_type, String name){
        this.gid=gid;
        this.resource_type = resource_type;
        this.name=name;
    }
    public Workspace(){}
}

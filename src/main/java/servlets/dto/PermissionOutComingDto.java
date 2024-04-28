package servlets.dto;

public class PermissionOutComingDto {
    private String id;
    private String permissionName;

    public PermissionOutComingDto() {}

    public PermissionOutComingDto(String id, String name) {
        this.id = id;
        this.permissionName = name;
    }

    public String getId() {
        return id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}

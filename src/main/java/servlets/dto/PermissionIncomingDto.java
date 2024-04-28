package servlets.dto;

public class PermissionIncomingDto {
    private String permissionName;

    public PermissionIncomingDto(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}

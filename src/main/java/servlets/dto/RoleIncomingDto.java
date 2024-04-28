package servlets.dto;

public class RoleIncomingDto {
    private String roleName;
    private String userId;

    public RoleIncomingDto() {}

    public RoleIncomingDto(String role, String userId) {
        this.roleName = role;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

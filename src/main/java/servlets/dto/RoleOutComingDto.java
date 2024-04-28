package servlets.dto;

import model.User;

import java.util.List;

public class RoleOutComingDto {
    private String id;
    private String name;
    private String userId;

    public RoleOutComingDto() {}

    public RoleOutComingDto(String id, String name, String userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}

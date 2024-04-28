package model;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private int id;
    private String name;
    private String usersId;
    private List<Permission> permissions;


    public Role(){
        this.permissions = new ArrayList<>();
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return usersId;
    }

    public void setUserId(String usersId) {
        this.usersId = usersId;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        permissions.remove(permission);
    }



}

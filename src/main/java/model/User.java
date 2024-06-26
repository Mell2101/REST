package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;

    private List<Role> roles;

    public User() {
        this.roles = new ArrayList<>();
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }
}

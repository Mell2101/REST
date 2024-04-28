package service;

import model.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role findRoleById(int id);
    Role updateRole(Role role, int id);
    boolean deleteRole(int id);
    List<Role> getAllRoles();
    List<Role> findByUserId(int userID);

}

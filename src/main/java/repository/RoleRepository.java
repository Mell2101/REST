package repository;

import model.Role;

import java.util.List;

public interface RoleRepository {
    boolean deleteById(int id);
    Role update(Role role, int id);
    Role getRoleById(int id);
    Role save(Role role);
    List<Role> getAllRoles ();
    List<Role> findByUserId(int userId);


}

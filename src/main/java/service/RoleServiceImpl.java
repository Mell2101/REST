package service;

import model.Role;
import repository.RoleRepository;
import repository.RoleRepositoryImpl;

import java.util.List;

public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    public RoleServiceImpl() {
        this.roleRepository = new RoleRepositoryImpl();
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        Role newRole = roleRepository.save(role);

        return newRole;
    }

    @Override
    public Role findRoleById(int id) {
        Role newRole = roleRepository.getRoleById(id);
        return  newRole;
    }

    @Override
    public Role updateRole(Role role, int id) {
        Role updateRole = roleRepository.update(role, id);

        return  updateRole;
    }

    @Override
    public boolean deleteRole(int id) {
        return roleRepository.deleteById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.getAllRoles();
        return roles;
    }

    @Override
    public List<Role> findByUserId(int userID) {
        List<Role> userRole = roleRepository.findByUserId(userID);
        return userRole;
    }
}

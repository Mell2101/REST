package service;

import model.Permission;
import repository.PermissionRepository;
import repository.PermissionRepositoryImpl;

import java.util.List;

public class PermissionServiceImpl implements PermissionService{
    private PermissionRepository permissionRepository;

    public PermissionServiceImpl() {
        this.permissionRepository = new PermissionRepositoryImpl();
    }

    public void setPermissionRepository(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission createPermission(Permission permission) {
        Permission newPermission = permissionRepository.save(permission);
        return newPermission;
    }

    @Override
    public Permission getPermissionById(int id) {
        Permission permission = permissionRepository.getPermissionById(id);
        return permission;
    }

    @Override
    public Permission updatePermission(Permission permission, int id) {
        Permission updatePermission = permissionRepository.update(permission,id);
        return updatePermission;
    }

    @Override
    public boolean deletePermission(int id) {
        return permissionRepository.deleteById(id);
    }

    @Override
    public List<Permission> getAllPermission() {
        List<Permission> permissions = permissionRepository.getAllPermission();
        return permissions;
    }
}

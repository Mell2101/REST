package repository;

import model.Permission;

import java.util.List;

public interface PermissionRepository {
    Permission save(Permission permission);
    List<Permission> getAllPermission();
    Permission update(Permission permission, int id);
    boolean deleteById(int id);
    Permission getPermissionById(int id);
}

package service;



import model.Permission;

import java.util.List;

public interface PermissionService {
    Permission createPermission(Permission permission);
    Permission getPermissionById(int id);
    Permission updatePermission(Permission permission, int id);
    boolean deletePermission(int id);
    List<Permission> getAllPermission();
}

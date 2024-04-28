package repository.mapper;

import model.Permission;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionResultMapImpl implements PermissionResultMap{
    @Override
    public Permission permissionMap(ResultSet resultSet) throws SQLException {
        Permission permission = new Permission();

        permission.setId(Integer.valueOf(resultSet.getString("permission_id")));
        permission.setName(resultSet.getString("permission_name"));

        return permission;
    }
}

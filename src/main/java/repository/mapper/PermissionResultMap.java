package repository.mapper;

import model.Permission;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PermissionResultMap {
    Permission permissionMap(ResultSet resultSet) throws SQLException;
}

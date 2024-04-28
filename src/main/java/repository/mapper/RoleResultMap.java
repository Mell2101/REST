package repository.mapper;

import model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RoleResultMap {
    Role roleMap(ResultSet resultSet) throws SQLException;
}

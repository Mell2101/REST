package repository.mapper;

import model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleResultMapImpl implements RoleResultMap{
    @Override
    public Role roleMap(ResultSet resultSet) throws SQLException {
        Role role = new Role();

        role.setId(Integer.valueOf(resultSet.getString("role_id")));
        role.setName(resultSet.getString("role_name"));
        role.setUserId(resultSet.getString("user_id"));


        return role;
    }
}

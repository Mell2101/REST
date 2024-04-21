package repository.mapper;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultMapImpl implements UserResultMap{
    @Override
    public User userMap(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(Integer.valueOf(resultSet.getString("user_id")));
        user.setUserName(resultSet.getString("user_name"));

        return user;
    }
}

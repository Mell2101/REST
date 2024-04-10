package repository.mapper;

import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserResultMap {
    User userMap(ResultSet resultSet) throws SQLException;
}

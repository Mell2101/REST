package repository;

import bd.ConnectionManager;
import bd.DataBaseConnectionManager;
import model.User;
import repository.mapper.UserResultMap;
import repository.mapper.UserResultMapImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private ConnectionManager connectionManager;
    private UserResultMap userResultMap;

    public UserRepositoryImpl() {
        this.userResultMap = new UserResultMapImpl();
        this.connectionManager = new DataBaseConnectionManager();
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public User getUserbyId(int id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE user_id = (?)"
            ))
        {
            preparedStatement.setString(1,String.valueOf(id));

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();

            if(resultSet.next()){
                user = userResultMap.userMap(resultSet);
            }
            connectionManager.closeConnection();
            return user;
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE user_id = (?)"
            ))
        {
            preparedStatement.setString(1, String.valueOf(id));
            return preparedStatement.execute();

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users"))
        {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                users.add(userResultMap.userMap(resultSet));
            }

            return  users;
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public User save(User user) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET user_name = ? WHERE user_id = ?"))
        {

            preparedStatement.setString(1, String.valueOf(user.getId()));
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.execute();

            return getUserbyId(user.getId());

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }


    @Override
    public User update(User user, int id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET user_name = ? WHERE user_id = ?"))
        {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, String.valueOf(id));
            preparedStatement.execute();

            return getUserbyId(id);
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }
}

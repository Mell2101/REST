package repository;
import bd.ConnectionManager;
import bd.DataBaseConnectionManager;
import model.Role;
import model.User;
import repository.mapper.RoleResultMap;
import repository.mapper.RoleResultMapImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository{

    private ConnectionManager connectionManager;
    private RoleResultMap roleResultMap;

    public RoleRepositoryImpl() {
        this.connectionManager = new DataBaseConnectionManager();
        this.roleResultMap = new RoleResultMapImpl();
    }
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Role getRoleById(int id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM role WHERE role_id = (?)"
            ))
        {
            preparedStatement.setString(1,String.valueOf(id));

            ResultSet resultSet = preparedStatement.executeQuery();
            Role role = new Role();

            if(resultSet.next()){
                role = roleResultMap.roleMap(resultSet);
            }

            connectionManager.closeConnection();

            return role;

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM role WHERE role_id = (?)"
            ))
        {
            preparedStatement.setString(1, String.valueOf(id));

            return preparedStatement.execute();

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Role update(Role role, int id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE role SET role_name = ? WHERE role_id = ?"))
        {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, String.valueOf(id));
            preparedStatement.execute();

            return getRoleById(id);
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Role save(Role role) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO role VALUES (?, ?)"))
        {

            preparedStatement.setString(1, String.valueOf(role.getId()));
            preparedStatement.setString(2, role.getName());
            preparedStatement.execute();

            return getRoleById(role.getId());

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Role> getAllRoles() {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM role"))
        {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Role> roles = new ArrayList<>();
            while (resultSet.next()){
                roles.add(roleResultMap.roleMap(resultSet));
            }

            return  roles;
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Role> findByUserId(int userId) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM role WHERE user_id = (?)"
            ))
        {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Role> roles = new ArrayList<>();

            while (resultSet.next()){
                roles.add(roleResultMap.roleMap(resultSet));
            }

            return  roles;
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }
}

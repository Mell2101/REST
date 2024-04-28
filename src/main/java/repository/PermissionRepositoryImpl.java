package repository;

import bd.ConnectionManager;
import bd.DataBaseConnectionManager;
import model.Permission;
import repository.mapper.PermissionResultMap;
import repository.mapper.PermissionResultMapImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermissionRepositoryImpl implements PermissionRepository{

    private PermissionResultMap permissionResultMap;
    private ConnectionManager connectionManager;

    public PermissionRepositoryImpl() {
        this.permissionResultMap = new PermissionResultMapImpl();
        this.connectionManager = new DataBaseConnectionManager();
    }


    @Override
    public Permission getPermissionById(int id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Permission WHERE permission_id = (?)"
            ))
        {
            preparedStatement.setString(1,String.valueOf(id));

            ResultSet resultSet = preparedStatement.executeQuery();
            Permission permission = new Permission();

            if(resultSet.next()){
                permission = permissionResultMap.permissionMap(resultSet);
            }
            connectionManager.closeConnection();

            return permission;

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Permission save(Permission permission) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Permission VALUES (?, ?)"))
        {

            preparedStatement.setString(1, String.valueOf(permission.getId()));
            preparedStatement.setString(2, permission.getName());
            preparedStatement.execute();

            return getPermissionById(permission.getId());

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Permission> getAllPermission() {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Permission"))
        {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Permission> permissions = new ArrayList<>();

            while (resultSet.next()){
                permissions.add(permissionResultMap.permissionMap(resultSet));
            }

            return  permissions;
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Permission update(Permission permission, int id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Permission SET permission_name = ? WHERE permission_id = ?"))
        {
            preparedStatement.setString(1, permission.getName());
            preparedStatement.setString(2, String.valueOf(id));
            preparedStatement.execute();

            return getPermissionById(id);

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Permission WHERE permission_id = (?)"
            ))
        {
            preparedStatement.setString(1, String.valueOf(id));
            return preparedStatement.execute();

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }
}

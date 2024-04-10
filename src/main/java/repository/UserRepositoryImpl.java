package repository;

import bd.ConnectionManager;
import model.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private ConnectionManager connectionManager;

    public UserRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public User getUserbyId(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user, int id) {
        return null;
    }
}

package service;

import model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User findeUserById(int id);
    User updateUser(User user, int id);
    boolean deleteUser(int id);
    List<User> getAllUsers();

}

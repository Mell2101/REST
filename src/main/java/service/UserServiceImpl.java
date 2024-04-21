package service;

import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User newUser) {
        User user = userRepository.save(newUser);
        return user;
    }

    @Override
    public User findeUserById(int id) {
        User user = userRepository.getUserbyId(id);
        return user;
    }

    @Override
    public User updateUser(User user, int id) {
        User userUpdate = userRepository.update(user, id);
        return userUpdate;
    }

    @Override
    public boolean deleteUser(int id) {
        return userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return users;
    }
}

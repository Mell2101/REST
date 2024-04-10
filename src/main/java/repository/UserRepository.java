package repository;


import model.User;

import java.util.List;

public interface UserRepository {
    User getUserbyId(int id);
    boolean deleteById(int id);
    List<User> getAllUsers();
    User save(User user);
    User update(User user, int id);


}

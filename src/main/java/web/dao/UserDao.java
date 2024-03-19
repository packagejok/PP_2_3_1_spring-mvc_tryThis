package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    User getUser(long id);

    User deleteUser(long id);
}

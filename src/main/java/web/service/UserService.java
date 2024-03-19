package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(long id);

    User deleteUser(long id);

    void createUser(User user);

    void updateUser(User user);
}

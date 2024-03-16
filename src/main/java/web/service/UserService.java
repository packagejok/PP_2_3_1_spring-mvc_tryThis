package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User readUser(long id);

    User deleteUser(long id);

    void createOrUpdateUser(User user);
}

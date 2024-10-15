package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(long id);
    void save(User user);
    void delete(long id);
    void update(long id, User user);
}

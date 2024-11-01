package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    @Transactional
    public List<User> getAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public User getById(long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        dao.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        dao.update(user);
    }
}

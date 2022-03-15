package com.sergax.crudrestapi.service;

import com.sergax.crudrestapi.dao.daoImpl.UserDaoImpl;
import com.sergax.crudrestapi.model.User;
import com.sergax.crudrestapi.dao.UserDao;

import java.util.List;

public class UserService implements UserDao {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    public boolean validate(String name, String password) {
        return userDao.validate(name, password);
    }
}

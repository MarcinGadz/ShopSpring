package com.mg.demo.service;

import com.mg.demo.dao.UserDAO;
import com.mg.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService implements Service<User> {

    private UserDAO dao;

    @Autowired
    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public User getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return dao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void add(User obj) {
        dao.save(obj);
    }

    @Override
    public User update(User obj) {
        return dao.save(obj);
    }
}

package com.mg.demo.service;

import com.mg.demo.dao.UserDAO;
import com.mg.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@org.springframework.stereotype.Service
public class UserService implements Service<User> {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    private PasswordEncoder encoder;

    public PasswordEncoder getEncoder() {
        return encoder;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    private UserDAO dao;

    @Autowired
    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public User findById(Long id) {
        return dao.findById(id).orElse(null);
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
    public User add(User obj) {
        obj.setPasswordHash(getEncoder().encode(obj.getPasswordHash()));
        return dao.save(obj);
    }

    @Override
    public User update(User obj) {
        obj.setPasswordHash(getEncoder().encode(obj.getPasswordHash()));
        return dao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return dao.existsById(id);
    }
}

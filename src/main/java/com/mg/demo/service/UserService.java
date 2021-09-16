package com.mg.demo.service;

import com.mg.demo.dao.RoleDAO;
import com.mg.demo.dao.UserDAO;
import com.mg.demo.entity.Role;
import com.mg.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
public class UserService implements Service<User> {

    private PasswordEncoder encoder;
    private final UserDAO dao;
    private final RoleDAO roleDAO;

    @Autowired
    public UserService(UserDAO dao, RoleDAO roleDAO) {
        this.dao = dao;
        this.roleDAO = roleDAO;
        roleDAO.save(customerRole);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public PasswordEncoder getEncoder() {
        return encoder;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
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

    private Role customerRole = new Role(1L, "CUSTOMER");

    @Override
    public User add(User obj) {
        obj.setPassword(getEncoder().encode(obj.getPassword()));
        obj.setRoles(Arrays.asList(customerRole));
        return dao.save(obj);
    }

    @Override
    public User update(User obj) {
        obj.setPassword(getEncoder().encode(obj.getPassword()));
        return dao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    public User findByUsername(String name) {
        return dao.findByUsername(name);
    }
}

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
import java.util.Collection;
import java.util.List;

@org.springframework.stereotype.Service
public class UserService implements Service<User> {

    private final UserDAO dao;
    private final RoleDAO roleDAO;
    private final Role customerRole;
    private PasswordEncoder encoder;

    @Autowired
    public UserService(UserDAO dao, RoleDAO roleDAO) {
        Role customerRoleTemp;
        this.dao = dao;
        this.roleDAO = roleDAO;
        customerRoleTemp = roleDAO.findByName("CUSTOMER");
        if (customerRoleTemp == null) {
            customerRoleTemp = new Role("CUSTOMER");
            roleDAO.save(customerRoleTemp);
        }
        this.customerRole = customerRoleTemp;
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

    @Override
    public User add(User obj) {
        obj.setPassword(getEncoder().encode(obj.getPassword()));
        Collection<Role> userRoles = obj.getRoles();
        if (userRoles == null) {
            userRoles = new ArrayList<>();
        }
        userRoles.add(customerRole);
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

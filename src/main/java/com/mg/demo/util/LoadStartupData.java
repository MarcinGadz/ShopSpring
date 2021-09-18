package com.mg.demo.util;

import com.mg.demo.dao.RoleDAO;
import com.mg.demo.entity.Role;
import com.mg.demo.entity.User;
import com.mg.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Component
public class LoadStartupData implements ApplicationRunner {

    private Service<User> userService;
    private RoleDAO roleDAO;

    @Autowired
    public void setUserService(Service<User> userService, RoleDAO roleDAO) {
        this.userService = userService;
        this.roleDAO = roleDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userService.getAll().isEmpty()) {
            Role adminRole = new Role("ADMIN");
            roleDAO.save(adminRole);
            Collection<Role> roles = new ArrayList<>();
            roles.add(adminRole);
            User admin = new User("admin", "adminpass", roles);
            userService.add(admin);
        }
    }
}

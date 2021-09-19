package com.mg.demo.util;

import com.mg.demo.dao.RoleDAO;
import com.mg.demo.entity.Item;
import com.mg.demo.entity.Role;
import com.mg.demo.entity.User;
import com.mg.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class LoadStartupData implements ApplicationRunner {

    private Service<User> userService;
    private RoleDAO roleDAO;
    private Service<Item> itemService;

    @Autowired
    public LoadStartupData(Service<User> userService, RoleDAO roleDAO, Service<Item> itemService) {
        this.userService = userService;
        this.roleDAO = roleDAO;
        this.itemService = itemService;
    }

    public LoadStartupData() {
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userService.getAll().isEmpty()) {
            addAdminToRepo();
        }
        if (itemService.getAll().isEmpty()) {
            addItemsToRepo();
        }

    }

    private void addAdminToRepo() {
        Role adminRole = new Role("ROLE_ADMIN");
        roleDAO.save(adminRole);
        Collection<Role> roles = new ArrayList<>();
        roles.add(adminRole);
        User admin = new User("admin", "adminpass", roles);
        userService.save(admin);
    }

    private void addItemsToRepo() {
        Item guitar = new Item("Electric guitar!", new BigDecimal("799"), "The best guitar in the world!");
        Item speakers = new Item("5.1 speakers", new BigDecimal("279.99"), "Good and cheap speakers set");
        Item hat = new Item("Red hat", new BigDecimal("79.99"), "Nice red hat");
        List<Item> items = Arrays.asList(guitar, speakers, hat);
        itemService.saveAll(items);
    }
}

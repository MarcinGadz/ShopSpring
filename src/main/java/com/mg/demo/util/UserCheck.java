package com.mg.demo.util;

import com.mg.demo.entity.User;
import com.mg.demo.service.Service;
import com.mg.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserCheck {

    private final UserService service;

    @Autowired
    public UserCheck(UserService service) {
        this.service = service;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public boolean checkId(Authentication auth, Long id) {
        logger.info("Checking id");
        Object principal = auth.getPrincipal();
        String userName = ((UserDetails)principal).getUsername();
        User p = service.findByUsername(userName);
        Long userId = p.getId();
        logger.info("User with id: " + userId + " is trying to access resources of user with id: " + id);
        return userId.equals(id);
    }
}

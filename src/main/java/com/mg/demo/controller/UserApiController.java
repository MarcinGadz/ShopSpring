package com.mg.demo.controller;

import com.mg.demo.entity.User;
import com.mg.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/users")
public class UserApiController extends ApiTemplateController<User> {
    private Service<User> service;

    @Autowired
    public UserApiController(Service<User> service) {
        this.service = service;
    }

    @PostConstruct
    private void setSuperService() {
        super.setService(service);
    }
}

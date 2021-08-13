package com.mg.demo.controller;

import com.mg.demo.entity.Order;
import com.mg.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/orders")
public class OrderApiController extends ApiTemplateController<Order> {
    private Service<Order> service;

    @Autowired
    public OrderApiController(Service<Order> service) {
        this.service = service;
    }

    @PostConstruct
    private void setSuperService() {
        super.setService(service);
    }
}

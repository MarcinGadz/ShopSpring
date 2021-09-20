package com.mg.demo.controller;

import com.mg.demo.entity.Order;
import com.mg.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/orders")
public class OrderApiController {
    private final OrderService service;

    @Autowired
    public OrderApiController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> getOrders(@PathVariable(name = "id") Long id) {
        return service.findByBuyerId(id);
    }
}

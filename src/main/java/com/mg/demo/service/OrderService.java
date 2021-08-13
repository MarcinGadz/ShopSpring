package com.mg.demo.service;

import com.mg.demo.dao.OrderDAO;
import com.mg.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService implements Service<Order> {

    private OrderDAO dao;

    @Autowired
    public OrderService(OrderDAO dao) {
        this.dao = dao;
    }

    @Override
    public Order getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return dao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void add(Order obj) {
        dao.save(obj);
    }

    @Override
    public Order update(Order obj) {
        return dao.save(obj);
    }
}

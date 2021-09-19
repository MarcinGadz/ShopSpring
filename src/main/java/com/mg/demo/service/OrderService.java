package com.mg.demo.service;

import com.mg.demo.dao.OrderDAO;
import com.mg.demo.entity.Item;
import com.mg.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class OrderService implements Service<Order> {

    private final OrderDAO dao;

    @Autowired
    public OrderService(OrderDAO dao) {
        this.dao = dao;
    }

    @Override
    public Order findById(Long id) {
        return dao.findById(id).orElse(null);
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
    public Order save(Order obj) {
        return dao.save(obj);
    }

    @Override
    public Order update(Order obj) {
        return dao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    @Override
    public List<Order> saveAll(Iterable<Order> objects) {
        return dao.saveAll(objects);
    }
}

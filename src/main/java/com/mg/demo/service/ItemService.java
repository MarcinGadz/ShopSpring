package com.mg.demo.service;

import com.mg.demo.dao.ItemDAO;
import com.mg.demo.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ItemService implements Service<Item> {

    private ItemDAO dao;

    @Autowired
    public ItemService(ItemDAO dao) {
        this.dao = dao;
    }

    @Override
    public Item findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Item> getAll() {
        return dao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Item add(Item obj) {
        return dao.save(obj);
    }

    @Override
    public Item update(Item obj) {
        return dao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return dao.existsById(id);
    }
}

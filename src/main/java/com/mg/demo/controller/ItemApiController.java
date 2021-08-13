package com.mg.demo.controller;

import com.mg.demo.entity.Item;
import com.mg.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemApiController {

    private Service<Item> service;

    @Autowired
    public ItemApiController(Service<Item> itemService) {
        this.service = itemService;
    }

    @GetMapping("/")
    public List<Item> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Item getOne(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public Item save(@RequestBody Item item) {
        service.add(item);
        return item;
    }

    @PutMapping("/")
    public Item update(@RequestBody Item item) {
        if (service.existsById(item.getId())) {
            service.update(item);
            return item;
        }
        //TODO throw proper exception
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }

}

package com.mg.demo.controller;

import com.mg.demo.entity.Item;
import com.mg.demo.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemApiController {

    private Service<Item> service;

    private final Logger logger = LoggerFactory.getLogger(ItemApiController.class.getName());

    @Autowired
    public ItemApiController(Service<Item> itemService) {
        this.service = itemService;
    }

    @GetMapping
    public List<Item> getAll() {
        logger.info("getting all items");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Item getOne(@PathVariable long id) {
        logger.info("Getting by id: " + id);
        return service.findById(id);
    }

    @PostMapping
    public Item save(@RequestBody Item item) {
        logger.info("Saving: " + item);
        service.add(item);
        logger.info("Saved: " + item);
        return item;
    }

    @PutMapping
    public Item update(@RequestBody Item item) {
        if (service.existsById(item.getId())) {
            logger.info("Updating existing item by: " + item);
            service.update(item);
            return item;
        }
        logger.info("Item with id " + item.getId() + " do not exists");
        //TODO throw proper exception
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        logger.info("Deleting by id: " + id);
        service.deleteById(id);
    }

}

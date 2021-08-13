package com.mg.demo.controller;

import com.mg.demo.entity.Item;
import com.mg.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("/items")
public class ItemApiController extends ApiTemplateController<Item> {

    private Service<Item> service;

    @Autowired
    public ItemApiController(Service<Item> service) {
        this.service = service;
    }

    @PostConstruct
    private void setSuperService() {
        //TODO try to find better solution
        super.setService(service);
    }

}

package com.mg.demo.service;

import java.util.List;

@org.springframework.stereotype.Service
public interface Service<T> {
    T getById(Long id);
    List<T> getAll();
    void deleteById(Long id);
    void add(T obj);
    T update(T obj);
}

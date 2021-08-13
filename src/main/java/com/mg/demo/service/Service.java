package com.mg.demo.service;

import java.util.List;

public interface Service<T> {
    T findById(Long id);
    List<T> getAll();
    void deleteById(Long id);
    T add(T obj);
    T update(T obj);
    boolean existsById(Long id);
}

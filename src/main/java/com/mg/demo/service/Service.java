package com.mg.demo.service;

import java.util.List;

public interface Service<T> {
    T findById(Long id);

    List<T> getAll();

    void deleteById(Long id);

    T save(T obj);

    List<T> saveAll(Iterable<T> objects);

    T update(T obj);

    boolean existsById(Long id);
}

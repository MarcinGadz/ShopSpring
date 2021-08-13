package com.mg.demo.dao;

import com.mg.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {
}

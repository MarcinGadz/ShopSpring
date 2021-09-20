package com.mg.demo.dao;

import com.mg.demo.entity.Order;
import com.mg.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {
    List<Order> findByBuyer(User buyer);
}

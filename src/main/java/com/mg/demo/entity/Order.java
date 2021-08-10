package com.mg.demo.entity;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private User buyer;
    private List<Item> items;

    public BigDecimal getCost() {
        BigDecimal sum = new BigDecimal(0);
        for (Item i : items) {
            sum = sum.add(i.getPrice());
        }
        return sum;
    }
}

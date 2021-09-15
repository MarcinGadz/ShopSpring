package com.mg.demo.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Order extends com.mg.demo.entity.Entity {

    @ManyToOne
    private User buyer;
    @OneToMany
    private List<Item> items;

    public BigDecimal getCost() {
        BigDecimal sum = new BigDecimal(0);
        for (Item i : items) {
            sum = sum.add(i.getPrice());
        }
        return sum;
    }
}

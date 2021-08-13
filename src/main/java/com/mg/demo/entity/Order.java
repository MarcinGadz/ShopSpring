package com.mg.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private User buyer;
    @OneToMany
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        BigDecimal sum = new BigDecimal(0);
        for (Item i : items) {
            sum = sum.add(i.getPrice());
        }
        return sum;
    }
}

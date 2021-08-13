package com.mg.demo.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Item extends com.mg.demo.entity.Entity {

    @Column
    private String name;
    @Column
    private BigDecimal price;
    @Column
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

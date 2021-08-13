package com.mg.demo.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class User extends com.mg.demo.entity.Entity {

    @Column
    private String username;
    @Column
    private String passwordHash;

    @OneToMany
    private List<Order> orders;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}

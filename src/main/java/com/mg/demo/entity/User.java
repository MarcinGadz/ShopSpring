package com.mg.demo.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class User extends com.mg.demo.entity.Entity {

    @Column
    private String username;
    @Column
    private String password;

    @OneToMany
    private List<Order> orders;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }
}

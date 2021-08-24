package com.mg.demo.util;

import com.mg.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserPrincipal implements UserDetails {
    private User user;

    Collection<? extends GrantedAuthority> authorities;
    public MyUserPrincipal(User user) {
        this.user = user;
    }

    public MyUserPrincipal(String subject, String password, Collection<? extends GrantedAuthority> authorities) {
        this.user = new User();
        this.user.setPasswordHash(password);
        this.user.setUsername(subject);
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
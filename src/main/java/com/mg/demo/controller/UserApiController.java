package com.mg.demo.controller;

import com.mg.demo.entity.User;
import com.mg.demo.service.Service;
import com.mg.demo.service.UserDetailsService;
import com.mg.demo.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/users")
public class UserApiController extends ApiTemplateController<User> {
    private AuthenticationManager manager;
    private UserDetailsService userDetailsService;
    private Service<User> service;
    private TokenProvider tokenProvider;

    @Autowired
    public void setTokenProvider(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Autowired
    public UserApiController(Service<User> service) {
        this.service = service;
    }


    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setManager(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostConstruct
    private void setSuperService() {
        super.setService(service);
    }

    @PostMapping("/authenticate")
    public String login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        Authentication auth;
        try {
            auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("INVALID_CREDENTIALS", e);
        } catch (DisabledException e) {
            throw new RuntimeException("USER_DISABLED", e);
        }
        UserDetails det = userDetailsService.loadUserByUsername(username);
        final String token = tokenProvider.createToken(auth);
        return token;
    }
}

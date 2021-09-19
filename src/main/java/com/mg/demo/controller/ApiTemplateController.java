package com.mg.demo.controller;

import com.mg.demo.entity.Entity;
import com.mg.demo.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public abstract class ApiTemplateController<T extends Entity> {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private Service<T> service;

    public ApiTemplateController() {

    }

    public void setService(Service<T> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> getAll() {
        logger.info("getting all items");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public T getOne(@PathVariable long id) {
        logger.info("Getting by id: " + id);
        return service.findById(id);
    }

    @PostMapping
    public T save(@RequestBody T item) {
        logger.info("Saving: " + item);
        service.save(item);
        logger.info("Saved: " + item);
        return item;
    }

    @PutMapping
    public T update(@RequestBody T item) {
        if (service.existsById(item.getId())) {
            logger.info("Updating existing item by: " + item);
            service.update(item);
            return item;
        }
        logger.info("Item with id " + item.getId() + " do not exists");
        //TODO throw proper exception
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        logger.info("Deleting by id: " + id);
        service.deleteById(id);
    }

//    private AuthenticationManager manager;
//    private UserDetailsService userDetailsService;
//    private TokenProvider tokenProvider;
//
//    @Autowired
//    public void setTokenProvider(TokenProvider tokenProvider) {
//        this.tokenProvider = tokenProvider;
//    }
//
//    @Autowired
//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Autowired
//    public void setManager(AuthenticationManager manager) {
//        this.manager = manager;
//    }
//
//
//    @PostMapping("/authenticate")
//    public String login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
//        logger.info("Authenticating...");
//        Authentication auth;
//        try {
//            auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (BadCredentialsException e) {
//            throw new RuntimeException("INVALID_CREDENTIALS", e);
//        } catch (DisabledException e) {
//            throw new RuntimeException("USER_DISABLED", e);
//        }
//        UserDetails det = userDetailsService.loadUserByUsername(username);
//        final String token = tokenProvider.createToken(auth);
//        return token;
//    }
}

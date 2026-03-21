package com.haruma.restful_web_services.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//Denotes a REST API
@RestController
public class UserResource {
    private UserDaoService service;
    public UserResource(UserDaoService service) {
        this.service = service;
    }

    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    //GET /users/1
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findById(id);
    }

    //POST /users
    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        service.save(user);
    }
}

package com.haruma.restful_web_services.user;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); // /users/4 => /users/{id}, user.getId
        return ResponseEntity.created(location).build();
    }
}

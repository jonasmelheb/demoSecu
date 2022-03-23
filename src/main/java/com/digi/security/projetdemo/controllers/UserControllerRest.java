package com.digi.security.projetdemo.controllers;

import com.digi.security.projetdemo.entities.User;
import com.digi.security.projetdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserControllerRest {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getOneByUserName(@PathVariable("username") String userName) {
        return new ResponseEntity<>(userRepository.findByUsername(userName), HttpStatus.OK);
    }
}

package com.example.springboot.controller;

import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/test")
    public String test() {
        return "Does /test work??";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "saved: " + user.getFirstName();
    }

    @PutMapping("/update/{uid}")
    public String updateUser(@PathVariable long uid, @RequestBody User user) {
        User oldUser = userRepo.findById(uid).get();

        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setAge(user.getAge());
        oldUser.setOccupation(user.getOccupation());

        userRepo.save(oldUser);
        return "updated: " + uid;
    }

    @DeleteMapping("/delete/{uid}")
    public String deleteUser(@PathVariable long uid) {
        User user = userRepo.findById(uid).get();

        userRepo.delete(user);
        return "deleted: " + uid;
    }
}

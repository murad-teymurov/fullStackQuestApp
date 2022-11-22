package com.company.questApp.controllers;

import com.company.questApp.entites.User;
import com.company.questApp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAll(){
       return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User newUser){
       return userService.addUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getByIdUser(@PathVariable Long UserId){
       return userService.getById(UserId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User newUser){
      return userService.updateUser(newUser,userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.removeById(userId);
    }
}

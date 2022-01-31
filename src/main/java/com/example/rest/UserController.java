package com.example.rest;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    public UserController(UserService usersService) {
        this.userService = usersService;
    }

    @PostMapping
    public ResponseEntity saveAllUsers (@RequestBody List<User> users) {
        userService.saveUsers(users);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<User>> getUserById (@PathVariable("id") Long id) {
        return new ResponseEntity(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateUserById(@RequestBody User user) {
        userService.updateUserById(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }*/

}

package com.example.rest;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private UserService userService;

    public UserController(UserService usersService) {
        this.userService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> saveAllUsers (@RequestBody User user) {
        return new ResponseEntity(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById (@PathVariable("userId") String userId) {
        User user = userService.getUserById(userId);
        if (user != null)
            return new ResponseEntity(user, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<List<User>> updateUserById(@RequestBody List<User> users) {
        return new ResponseEntity(userService.updateUsers(users), HttpStatus.OK);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }*/

}

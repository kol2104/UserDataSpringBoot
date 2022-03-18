package com.example.rest;

import com.example.domain.User;
import com.example.exception.UserNotFoundException;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService usersService) {
        this.userService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser (@RequestBody User user) {
        return new ResponseEntity(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById (@PathVariable("userId") String userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent())
            return new ResponseEntity(userOptional, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<List<User>> updateUsers(@RequestBody List<User> users) {
        return new ResponseEntity(userService.updateUsers(users), HttpStatus.OK);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity handleException(Exception exception) {
        Object errorBody = exception.getMessage();
        return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }*/

}

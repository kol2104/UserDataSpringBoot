package com.example.rest;

import com.example.domain.User;
import com.example.domain.UserWishes;
import com.example.service.UserWishesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-wishes")
public class UserWishesController {

    private UserWishesService userWishesService;

    public UserWishesController(UserWishesService userWishesService) {
        this.userWishesService = userWishesService;
    }

    @PostMapping
    public ResponseEntity saveAllUserWishes (@RequestBody List<UserWishes> userWishes) {
        userWishesService.saveUsers(userWishes);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity< List<User> > getAllUserWhishes () {
        return new ResponseEntity(userWishesService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<User>> getUserWishesById (@PathVariable("id") String id) {
        return new ResponseEntity(userWishesService.getUserWishesById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateUserWishesById(@RequestBody UserWishes userWishes) {
        userWishesService.updateUserWishesById(userWishes);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        userWishesService.deleteUserWishesById(id);
        return new ResponseEntity(HttpStatus.OK);
    }*/
}

package com.example.service;

import com.example.domain.User;
import com.example.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import com.example.persistence.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserWishesService userWishesService;

    public UserService(UserRepository userRepository, UserWishesService userWishesService) {
        this.userRepository = userRepository;
        this.userWishesService = userWishesService;
    }

    public List<User> getUsers () {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public User saveUser (User user) {
        User newUser = User.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
        user.setId(userRepository.save(newUser).getId());
        user.setUserWishes(userWishesService.saveUserWishes(user.getUserWishes(), user));
        return user;
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Transactional
    public List<User> updateUsers(List<User> users) {
        for (User user : users) {
            userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User id not exist: " + user.getId()));
            user.setUserWishes(userWishesService.saveUserWishes(user.getUserWishes(), user));
            userRepository.save(user);
        }
        return users;
    }

    /*public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }*/
}

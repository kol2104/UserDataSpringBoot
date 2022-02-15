package com.example.service;

import com.example.domain.User;
import com.example.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import com.example.persistence.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        user.setId(UUID.randomUUID().toString());
        userWishesService.saveUserWishes(user.getUserWishes(), user);
        return userRepository.save(user);
    }

    public User getUserById(String id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            user = optionalUser.get();
        return user;
    }

    @Transactional
    public List<User> updateUsers(List<User> users) {
        for (User user : users) {
            userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException(user.getId()));
            userWishesService.saveUserWishes(user.getUserWishes(), user);
            userRepository.save(user);
        }
        return users;
    }

    /*public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }*/
}

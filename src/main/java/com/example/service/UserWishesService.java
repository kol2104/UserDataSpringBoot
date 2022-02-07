package com.example.service;

import com.example.domain.UserWishes;
import com.example.persistence.UserWishesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserWishesService {

    private UserWishesRepository userWishesRepository;
    private UserService userService;

    public UserWishesService(UserWishesRepository userWishesRepository, UserService userService) {
        this.userWishesRepository = userWishesRepository;
        this.userService = userService;
    }

    public List<UserWishes> getUsers () {
        return (List<UserWishes>) userWishesRepository.findAll();
    }

    public void saveUsers (List<UserWishes> userWishesList) {
        for (UserWishes userWishes : userWishesList) {
            userWishes.setUser(userService.getUserById(userWishes.getUserId()));
        }
        userWishesRepository.saveAll(userWishesList);
    }


    public UserWishes getUserWishesById(String id) {
        UserWishes userWishes = null;
        Optional<UserWishes> optionalUserWishes = userWishesRepository.findById(id);
        if (optionalUserWishes.isPresent())
            userWishes = optionalUserWishes.get();
        return userWishes;
    }

    public void updateUserWishesById(UserWishes userWishes) {
        Optional<UserWishes> optionalUserWishes = userWishesRepository.findById(userWishes.getId());
        if (optionalUserWishes.isPresent()) {
            userWishes.setUser(userService.getUserById(userWishes.getUserId()));
            userWishesRepository.save(userWishes);
        }
    }


    /*public void deleteUserWishesById(Long id) {
        userWishesRepository.deleteById(id);
    }*/
}

package com.example.service;

import com.example.domain.User;
import com.example.domain.UserWish;
import com.example.persistence.UserWishesRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserWishesService {

    private UserWishesRepository userWishesRepository;

    public UserWishesService(UserWishesRepository userWishesRepository) {
        this.userWishesRepository = userWishesRepository;
    }

    public void saveUserWishes(Set<UserWish> userWishes, User user) {
        if (userWishes == null)
            return;
        for (UserWish userWish : userWishes) {
            userWish.setUserId(user.getId());
        }
        userWishesRepository.saveAll(userWishes);
    }

    /*public void deleteUserWishesById(Long id) {
        userWishesRepository.deleteById(id);
    }*/
}

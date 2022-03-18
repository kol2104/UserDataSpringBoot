package com.example.service;

import com.example.domain.User;
import com.example.domain.UserWish;
import com.example.persistence.UserWishesRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserWishesService {

    private UserWishesRepository userWishesRepository;

    public UserWishesService(UserWishesRepository userWishesRepository) {
        this.userWishesRepository = userWishesRepository;
    }

    public Set<UserWish> saveUserWishes(Set<UserWish> userWishes, User user) {
        if (userWishes == null)
            return null;
        for (UserWish userWish : userWishes) {
            userWish.setUserId(user.getId());
        }
        Set<UserWish> userWishSet = new HashSet<>();
        userWishesRepository.saveAll(userWishes).forEach(userWish -> userWishSet.add(userWish));
        return userWishSet;
    }

    /*public void deleteUserWishesById(Long id) {
        userWishesRepository.deleteById(id);
    }*/
}

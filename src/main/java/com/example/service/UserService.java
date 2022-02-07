package com.example.service;

import com.example.domain.User;
import org.springframework.stereotype.Service;
import com.example.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers () {
        return (List<User>) userRepository.findAll();
    }

    public User saveUser (User user) {
        return userRepository.save(user);
    }

    public User getUserById(String id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            user = optionalUser.get();
        return user;
    }

    public List<User> updateUsers(List<User> users) {
        List<User> updateUserList = new ArrayList<>();
        for (User user : users) {
            Optional<User> optionalUser = userRepository.findById(user.getId());
            if (optionalUser.isPresent()) {
                userRepository.save(user);
                updateUserList.add(user);
            }
        }
        return updateUserList;
    }

    /*public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }*/
}

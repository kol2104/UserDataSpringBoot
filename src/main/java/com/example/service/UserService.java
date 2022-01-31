package com.example.service;

import com.example.domain.User;
import org.springframework.stereotype.Service;
import com.example.persistence.UserRepository;

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

    public void saveUsers (List<User> users) {
        userRepository.saveAll(users);
    }

    public User getUserById(long id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            user = optionalUser.get();
        return user;
    }

    public void updateUserById(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            userRepository.save(user);
        }
    }

    /*public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }*/
}

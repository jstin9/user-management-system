package com.jstn9.usermanagementsystem.services;

import com.jstn9.usermanagementsystem.exceptions.UserNotFoundException;
import com.jstn9.usermanagementsystem.models.User;
import com.jstn9.usermanagementsystem.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return findUserOrThrow(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        findUserOrThrow(id);
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        findUserOrThrow(id);
        userRepository.deleteById(id);
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

}

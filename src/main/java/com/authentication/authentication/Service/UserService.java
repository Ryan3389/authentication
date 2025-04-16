package com.authentication.authentication.Service;

import com.authentication.authentication.Model.User;
import com.authentication.authentication.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, EmailService emailService) {
         this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        // Creates an empty list
        List<User> users = new ArrayList<>();

        // Creates an iterable object of all users in the database
        Iterable<User> iterable = userRepository.findAll();

        for(User user : iterable) {
            users.add(user);
        }

        return users;
    }
}

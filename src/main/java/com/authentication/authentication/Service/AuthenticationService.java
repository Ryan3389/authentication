package com.authentication.authentication.Service;

import com.authentication.authentication.DTO.RegisterUserDTO;
import com.authentication.authentication.Model.User;
import com.authentication.authentication.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public User registerUser(RegisterUserDTO userInput) {
        // Add user details
        User user = new User(userInput.getUsername(), userInput.getEmail(), passwordEncoder.encode(userInput.getPassword()));
        // Set the verification code by calling generate code method
        user.setVerificationCode(generateVerificationCode());
        // Set when the verification code expires
        user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(10));
        // Set enabled to false - this will be flipped once user submits verification code
        user.setEnabled(false);
        // Return user
        return userRepository.save(user);
    }

    public String generateVerificationCode() {
        // Use this to generate random code
        Random random = new Random();
        int verificationCode = random.nextInt(999999) + 100000;
        return String.valueOf(verificationCode);
    }
}


package com.authentication.authentication.Service;

import com.authentication.authentication.DTO.LoginDTO;
import com.authentication.authentication.DTO.RegisterUserDTO;
import com.authentication.authentication.DTO.VerifyDTO;
import com.authentication.authentication.Model.User;
import com.authentication.authentication.Repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
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
        sendVerificationEmail(user);
        // Return user
        return userRepository.save(user);
    }


    public User loginUser(LoginDTO userInput) {
        // Find user by email
        User user = userRepository.findByEmail(userInput.getEmail())
                .orElseThrow(() -> new RuntimeException("Could not find user with email " + userInput.getEmail()));
        if(!user.isEnabled()) {
            throw new RuntimeException("Please verify your account before logging in");
        }

        // Verifies if user email/password is correct
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(userInput.getEmail(), userInput.getPassword()));

        return user;
    }

    public void verifyUser(VerifyDTO userInput) {
        Optional<User> optionalUser = userRepository.findByEmail(userInput.getEmail());

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            if(user.getVerificationCodeExpiration().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Please verify your account before logging in");
            }

            if(user.getVerificationCode().equals(generateVerificationCode())) {
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiration(null);
                userRepository.save(user);
            }
            else {
                throw new RuntimeException("Invalid verification code");
            }

        } else {
            throw new RuntimeException("Could not find user with email " + userInput.getEmail());
        }
    }

    public void resendVerificationCode(String email) {
        // Find the user by their email
        Optional<User> optionalUser = userRepository.findByEmail(email);
        // Set isEnabled to false
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.isEnabled()) {
                throw new RuntimeException("User is already verified");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(10));
            sendVerificationEmail(user);
            userRepository.save(user);

        } else {
            throw new RuntimeException("Could not find user with email " + email);
        }
    }


    public void sendVerificationEmail(User user)  {
        String emailSubject = "Verify Your Account";
        String verificationCode = "Verification Code: " + user.getVerificationCode();

        String emailMessage = "<html>" +
                "<body style=\"font-family: Arial, sans-serif;\">" +
                    "<div style=\"background-color:#f5f5f5; padding:20px;\">"+
                        "<h2 style=\"color: #333;>Verify Your Account</h2>\"" +
                        "<p style=\"font-size: 18px;\">Enter the code below:</p>" +
                        "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); \">" +
                            "<h3 style=\"color: #333;\">Verification Code: </h3>" +
                            "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"+
                        "</div" +
                    "</div>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendEmail(user.getEmail(), emailSubject, emailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public String generateVerificationCode() {
        // Use this to generate random code
        Random random = new Random();
        int verificationCode = random.nextInt(999999) + 100000;
        return String.valueOf(verificationCode);
    }
}


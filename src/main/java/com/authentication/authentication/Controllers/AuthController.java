package com.authentication.authentication.Controllers;

import com.authentication.authentication.DTO.LoginDTO;
import com.authentication.authentication.DTO.RegisterUserDTO;
import com.authentication.authentication.DTO.VerifyDTO;
import com.authentication.authentication.Model.User;
import com.authentication.authentication.Response.UserResponse;
import com.authentication.authentication.Service.AuthenticationService;
import com.authentication.authentication.Service.JwtService;
import com.authentication.authentication.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthController(UserService userService, JwtService jwtService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }
    // Sign up
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterUserDTO registerUserDTO) {
        User newUser = authenticationService.registerUser(registerUserDTO);
        return ResponseEntity.ok(newUser);
    }
    // Login
    @PostMapping("/login")
    public ResponseEntity<UserResponse>login(@RequestBody LoginDTO loginDTO) {
        // Login user
        User currentUser = authenticationService.loginUser(loginDTO);
        // Generate token
        String token = jwtService.generateToken(currentUser);
        // Generate response
        String statusMessage = "Login Successful";
        UserResponse userResponse = new UserResponse(token, jwtService.getTokenExpirationTime(), statusMessage);
        // Return - response
        return ResponseEntity.ok(userResponse);
    }

    // Verify
    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyDTO verifyDTO) {
        try{
            authenticationService.verifyUser(verifyDTO);
            String message = "Account Verified";
            String jsonString = "\"" + message + "\"";
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonString);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Resend
    @PostMapping("/resend")
    public ResponseEntity<String> resendVerificationCode(@RequestBody  String email) {
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification Code Resent to" + email);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

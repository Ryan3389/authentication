package com.authentication.authentication.Controllers;


import com.authentication.authentication.DTO.RegisterUserDTO;
import com.authentication.authentication.Model.User;
import com.authentication.authentication.Repository.UserRepository;
import com.authentication.authentication.Service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = " http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @GetMapping("/getALLUsers")
   public ResponseEntity<List<User>> getAllUsers() {
        Iterable<User> iterable = userRepository.findAll();
        List<User> users = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<User>deleteUser(@PathVariable int id) {
      User user = userRepository.deleteById(id);
      return ResponseEntity.ok(user);
   }


}

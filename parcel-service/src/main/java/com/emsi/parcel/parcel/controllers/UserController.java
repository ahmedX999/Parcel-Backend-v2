package com.emsi.parcel.parcel.controllers;


import com.emsi.parcel.parcel.auth.AuthenticationRequest;
import com.emsi.parcel.parcel.auth.AuthenticationResponse;
import com.emsi.parcel.parcel.auth.RegisterRequest;
import com.emsi.parcel.parcel.entities.User;
import com.emsi.parcel.parcel.repositories.UserRepository;
import com.emsi.parcel.parcel.services.AuthenticationService;
import com.emsi.parcel.parcel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    @PostMapping("/save")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @GetMapping("/findByUsername/{username}")
    public Optional<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            // Check if the email already exists
            if (userRepository.existsByEmail(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists. Please use a different email.");
            }

            // If email doesn't exist, proceed with user registration
            AuthenticationResponse response = authenticationService.register(request);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("username/{username}")
    public Optional<User> findByUser(@PathVariable String username){
        return userRepository.findByUsername(username);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
    @GetMapping("email/{email}")
    public User findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
}

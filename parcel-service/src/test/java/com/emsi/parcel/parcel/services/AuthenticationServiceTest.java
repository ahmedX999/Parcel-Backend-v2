package com.emsi.parcel.parcel.services;

import com.emsi.parcel.parcel.auth.AuthenticationRequest;
import com.emsi.parcel.parcel.auth.AuthenticationResponse;
import com.emsi.parcel.parcel.auth.RegisterRequest;
import com.emsi.parcel.parcel.configurations.JwtService;
import com.emsi.parcel.parcel.entities.Role;
import com.emsi.parcel.parcel.entities.User;
import com.emsi.parcel.parcel.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void register() {
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "john@example.com", "johnny", "password");
        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password("encodedPassword") // You may need to adjust this based on your password encoder logic
                .role(Role.USER)
                .build();
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(user);
        when(jwtService.generateToken(any())).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationService.register(registerRequest);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
        verify(userRepository, times(1)).save(any());
        verify(jwtService, times(1)).generateToken(any());
    }

    @Test
    void authenticate() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("johnny", "password");
        User user = User.builder()
                .username("johnny")
                .password("encodedPassword") // You may need to adjust this based on your password encoder logic
                .build();
        when(userRepository.findByUsername(authenticationRequest.getUsername())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any())).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtService, times(1)).generateToken(any());
    }


}

package com.ninova.ninova.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.ninova.ninova.dto.AuthenticationRequest;
import com.ninova.ninova.dto.AuthenticationResponse;
import com.ninova.ninova.repository.UserRepository;
import com.ninova.ninova.util.JwtUtil;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        UserDetails userDetails = userRepository.findByUsername(authRequest.getUsername()).orElseThrow();
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

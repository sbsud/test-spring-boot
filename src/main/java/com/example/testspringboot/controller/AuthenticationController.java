package com.example.testspringboot.controller;

import com.example.testspringboot.model.*;
import com.example.testspringboot.repository.ApplicationUserRepository;
import com.example.testspringboot.repository.TokenRepository;
import com.example.testspringboot.services.ApplicationUserDetailsService;
import com.example.testspringboot.services.TokenService;
import com.example.testspringboot.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/api/signup")
    public ResponseEntity<?> signup(@Validated @RequestBody ApplicationUser user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        applicationUserRepository.save(user);
        return ResponseEntity.ok("Successfully signed up.");
    }

    @PostMapping(value = "/api/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);

        tokenService.save(authRequest.getUsername(), jwt);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(value = "/api/logout")
    public ResponseEntity<?> deleteAuthToken(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");

        String username = jwtUtil.getUsernameFromAuthorizationHeader(authHeader);
        tokenService.delete(username);

        return ResponseEntity.ok("Successfully logged out.");
    }
}

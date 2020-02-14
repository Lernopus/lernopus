package com.lernopus.lernopus.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lernopus.lernopus.exception.AppException;
import com.lernopus.lernopus.model.AuthProvider;
import com.lernopus.lernopus.model.LaLearnRole;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.model.LaRoleName;
import com.lernopus.lernopus.payload.JwtAuthenticationResponse;
import com.lernopus.lernopus.payload.LaApiResponse;
import com.lernopus.lernopus.payload.LoginRequest;
import com.lernopus.lernopus.payload.SignUpRequest;
import com.lernopus.lernopus.repository.LaRoleRepository;
import com.lernopus.lernopus.repository.LaUserRepository;
import com.lernopus.lernopus.security.TokenProvider;

/**
 * Created by amernath v on 2019-09-04.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    LaUserRepository userRepository;

    @Autowired
    LaRoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLaUserNameOrLaMailId(),
                        loginRequest.getLaPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByLaUserName(signUpRequest.getLaUserName())) {
            return new ResponseEntity<Object>(new LaApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByLaMailId(signUpRequest.getLaMailId())) {
            return new ResponseEntity<Object>(new LaApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        if(userRepository.existsByLaPhoneNumber(signUpRequest.getLaPhoneNumber())) {
            return new ResponseEntity<Object>(new LaApiResponse(false, "Phone Number already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        LaLearnUser user = new LaLearnUser(signUpRequest.getLaUserName(), signUpRequest.getLaUserFullName(), signUpRequest.getLaMailId(), signUpRequest.getLaPhoneNumber(), signUpRequest.getLaImagePath(), signUpRequest.getLaPassword());

        user.setLaPassword(passwordEncoder.encode(user.getLaPassword()));
        user.setProvider(AuthProvider.local);

        LaLearnRole userRole = roleRepository.findByLaRoleName(LaRoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setLaRoles(Collections.singleton(userRole));

        LaLearnUser result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getLaUserName()).toUri();

        return ResponseEntity.created(location).body(new LaApiResponse(true, "User registered successfully"));
    }
}

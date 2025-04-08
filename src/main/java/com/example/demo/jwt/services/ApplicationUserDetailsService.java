package com.example.demo.jwt.services;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }

    private Boolean verifyPasswordHash(String username, String password) {
        if (password.isBlank() || password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty or whitespace only string.");
        var userDetails = loadUserByUsername(username);

    }

    public UserDetails authenticate(String username, String password) throws NoSuchAlgorithmException {
        if (username.isEmpty() || password.isEmpty())
            throw new BadCredentialsException("Unauthorized");
        var userDetails = userService.loadUserByUsername(username);
        if (userDetails == null) throw new BadCredentialsException("Unauthorized");
        if (userDetails.getPassword().equals(password)) {
            return userDetails;
        } else t
        ;
    }
}

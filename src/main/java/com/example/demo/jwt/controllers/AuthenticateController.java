package com.example.demo.jwt.controllers;

import com.example.demo.jwt.models.AuthenticationRequest;
import com.example.demo.jwt.models.AuthenticationResponse;
import com.example.demo.jwt.util.JwtUtil;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticateController {

    private final JwtUtil jwtTokenUtil;
    private final UserService userService;

    @RequestMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest req) throws Exception {
        UserDetails user;
        try {
            log.info("Authenticating user: {}", req.getUsername());
            user = userService.authenticate(req.getUsername(), req.getPassword());
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        var jwt = jwtTokenUtil.generateToken(user);
        return new AuthenticationResponse(jwt);
    }
}

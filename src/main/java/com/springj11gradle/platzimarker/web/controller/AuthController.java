package com.springj11gradle.platzimarker.web.controller;

import com.springj11gradle.platzimarker.domain.dto.AuthenticationRequest;
import com.springj11gradle.platzimarker.domain.dto.AuthenticationResponse;
import com.springj11gradle.platzimarker.domain.service.UserServiceDetails;
import com.springj11gradle.platzimarker.web.security.JWUTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceDetails userServiceDetails;
    @Autowired
    private JWUTutil jwuTutil;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createtoken(@RequestBody AuthenticationRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (request.getUsername(),request.getPassword()));
            UserDetails userDetails =userServiceDetails.loadUserByUsername(request.getUsername());
            String jwt =jwuTutil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt),HttpStatus.OK);

        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }



    }
}

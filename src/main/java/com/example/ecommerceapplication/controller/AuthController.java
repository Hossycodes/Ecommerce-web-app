package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.LoginRequest;
import com.example.ecommerceapplication.dto.LoginResponse;
import com.example.ecommerceapplication.dto.RegistrationRequest;
import com.example.ecommerceapplication.entitites.LocalUser;
import com.example.ecommerceapplication.exceptions.UserAlreadyExistsException;
import com.example.ecommerceapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
            try {
                userService.registerUser(registrationRequest);
                return ResponseEntity.ok().build();
            } catch (UserAlreadyExistsException ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest){
        String jwt = userService.loginUser(loginRequest);
        if(jwt== null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }
    @GetMapping("/me")
    public LocalUser getLoggedInUserProfile(@AuthenticationPrincipal LocalUser user){
        return user;
    }

}

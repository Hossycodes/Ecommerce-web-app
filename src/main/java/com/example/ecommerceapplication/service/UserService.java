package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.dto.LoginRequest;
import com.example.ecommerceapplication.dto.RegistrationRequest;
import com.example.ecommerceapplication.entitites.LocalUser;

public interface UserService {
    public LocalUser registerUser(RegistrationRequest registrationRequest);
    public String loginUser(LoginRequest loginRequest);

}

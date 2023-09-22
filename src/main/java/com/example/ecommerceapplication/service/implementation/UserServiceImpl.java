package com.example.ecommerceapplication.service.implementation;

import com.example.ecommerceapplication.dto.LoginRequest;
import com.example.ecommerceapplication.dto.RegistrationRequest;
import com.example.ecommerceapplication.entitites.LocalUser;
import com.example.ecommerceapplication.entitites.VerificationToken;
import com.example.ecommerceapplication.exceptions.UserAlreadyExistsException;
import com.example.ecommerceapplication.repository.LocalUserRepository;
import com.example.ecommerceapplication.repository.VerificationTokenRepository;
import com.example.ecommerceapplication.service.EncryptionService;
import com.example.ecommerceapplication.service.JWTService;
import com.example.ecommerceapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final LocalUserRepository localUserRepository;
    private final  EncryptionService encryptionService;
    private final JWTService jwtService;
    private final VerificationTokenRepository verificationTokenRepository;



    @Override
    public LocalUser registerUser(RegistrationRequest registrationRequest) throws  UserAlreadyExistsException {
        if (localUserRepository.findByEmailIgnoreCase(registrationRequest.getEmail()).isPresent()
                || localUserRepository.findByUsernameIgnoreCase(registrationRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("user already exists", HttpStatus.BAD_REQUEST);
        }
        LocalUser user = new LocalUser();
        user.setEmail(registrationRequest.getEmail());
        user.setUsername(registrationRequest.getUsername());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationRequest.getPassword()));
        return localUserRepository.save(user);
    }
    private VerificationToken createVerificationToken(LocalUser user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);
        user.getVerificationTokens.add(verificationToken);


        return verificationToken;
    }

    @Override
    public String loginUser(LoginRequest loginRequest) {
        Optional<LocalUser> opUser = localUserRepository.findByUsernameIgnoreCase(loginRequest.getUsername());
        if(opUser.isPresent()){
            LocalUser user = opUser.get();
            if(encryptionService.verifyPassword(loginRequest.getPassword(), user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

    public EncryptionService getEncryptionService() {
        return encryptionService;
    }
}


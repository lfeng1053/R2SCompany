package com.example.demo.service.impl;

import com.example.demo.Exception.BusinessConflictException;
import com.example.demo.Exception.BusinessValidationException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RegisterResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.security.JwtService;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    @Override
    public RegisterResponse register(RegisterRequest request) {
        //Check username if already exists
        if(userRepository.existsByUsername(request.getUsername())){
            throw new BusinessConflictException("Username already exists");
        }
        // Find default role
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(()-> new ResourceNotFoundException("Default role USER not found"));

        //Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        //Encode password
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(userRole));

        //Save user to DB
        userRepository.save(user);

        return new RegisterResponse("Register successfully");
    }

    @Override
    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessValidationException("Invalid username or password"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BusinessValidationException("Invalid user or password");
        }
        String token = jwtService.generateToken(user);
        long exp = jwtService.getExpirationEpochSeconds(token);

        return new LoginResponse(token, exp);
    }


}

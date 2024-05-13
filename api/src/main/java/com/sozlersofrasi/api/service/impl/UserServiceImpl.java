package com.sozlersofrasi.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sozlersofrasi.api.model.entity.User;
import com.sozlersofrasi.api.repository.UserRepository;
import com.sozlersofrasi.api.service.JwtService;
import com.sozlersofrasi.api.service.UserService;
import com.sozlersofrasi.api.utilities.DataResult;
import com.sozlersofrasi.api.utilities.Result;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public Result login(String username, String password) {
        
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtService.generateToken(user.getUsername());
            return new DataResult<>(token, 200, "Login successful.");
        } else {
            return new Result(401, "Invalid username or password.");
        }
        
    }

    @Override
    public Result register(String username, String email, String password) {
        
        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            return new Result(501, "Username or email already exists.");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);

        return new DataResult<>(newUser, 200,"User successfully registered.");
        
    }

 
    
}

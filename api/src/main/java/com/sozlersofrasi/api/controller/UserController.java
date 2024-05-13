package com.sozlersofrasi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sozlersofrasi.api.model.entity.User;
import com.sozlersofrasi.api.service.UserService;
import com.sozlersofrasi.api.utilities.Result;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseEntity<Result> get() {
        Result result = new Result(501,"Message");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<Result> registerUser(@RequestBody User user) {
        Result result = userService.register(user.getUsername(), user.getEmail(), user.getPassword());
        if (result.getStatusCode() == 200) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity
                .status(result.getStatusCode())
                .body(result);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Result> loginUser(@RequestBody User user) {
        Result result = userService.login(user.getUsername(), user.getPassword());
        if (result.getStatusCode() == 200) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity
                .status(result.getStatusCode())
                .body(result);
        }
    }

}

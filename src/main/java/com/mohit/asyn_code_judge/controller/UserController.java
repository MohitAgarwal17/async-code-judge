package com.mohit.asyn_code_judge.controller;

import com.mohit.asyn_code_judge.dto.CreateUserRequest;
import com.mohit.asyn_code_judge.dto.UserResponse;
import com.mohit.asyn_code_judge.entity.User;
import com.mohit.asyn_code_judge.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest req) {
        User saved = userService.create(req);
        return ResponseEntity
                .created(URI.create("/api/users/" + saved.getId()))
                .body(UserResponse.from(saved));
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        return UserResponse.from(userService.get(id));
    }
}
package com.mohit.asyn_code_judge.dto;

import com.mohit.asyn_code_judge.entity.User;
import java.time.Instant;

public record UserResponse(Long id, String username, String email, Instant createdAt) {
    public static UserResponse from(User u) {
        return new UserResponse(u.getId(), u.getUsername(), u.getEmail(), u.getCreatedAt());
    }
}
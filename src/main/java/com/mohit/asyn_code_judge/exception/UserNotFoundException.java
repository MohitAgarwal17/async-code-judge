package com.mohit.asyn_code_judge.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found with id " + id);
    }
}
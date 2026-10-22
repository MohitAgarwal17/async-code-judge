package com.mohit.asyn_code_judge.exception;

public class ProblemNotFoundException extends RuntimeException {

    public ProblemNotFoundException(Long id) {
        super("Problem not found with id " + id);
    }
}
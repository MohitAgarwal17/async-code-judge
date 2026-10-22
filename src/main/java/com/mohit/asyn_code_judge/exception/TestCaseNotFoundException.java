package com.mohit.asyn_code_judge.exception;

public class TestCaseNotFoundException extends RuntimeException {

    public TestCaseNotFoundException(Long id) {
        super("Test case not found with id " + id);
    }
}
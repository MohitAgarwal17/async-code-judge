package com.mohit.asyn_code_judge.exception;

public class SubmissionNotFoundException extends RuntimeException {
    public SubmissionNotFoundException(Long id) {
        super("Submission " + id + " not found");
    }
}

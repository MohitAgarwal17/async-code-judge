package com.mohit.asyn_code_judge.exception;

public class UnsupportedLanguageException extends RuntimeException {
    public UnsupportedLanguageException(String language) {
        super(language + " is not supported");
    }
}

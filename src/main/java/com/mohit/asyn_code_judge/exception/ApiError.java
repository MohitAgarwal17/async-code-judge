package com.mohit.asyn_code_judge.exception;

public record ApiError(int status, String error, String message) {

}
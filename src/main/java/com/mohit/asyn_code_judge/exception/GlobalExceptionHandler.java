package com.mohit.asyn_code_judge.exception;

import com.mohit.asyn_code_judge.entity.SubmissionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SubmissionNotFoundException.class)
    public ResponseEntity<ApiError> submissionNotFound(SubmissionNotFoundException ex) {
        ApiError body = new ApiError(404, "Not Found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(UnsupportedLanguageException.class)
    public ResponseEntity<ApiError> languageNotFound(UnsupportedLanguageException ex) {
        ApiError body = new ApiError(400, "Bad Request", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<ApiError> handleUnexpectedException(java.lang.Exception ex) {
        if (ex instanceof ErrorResponse errorResponse && errorResponse.getStatusCode().is4xxClientError()) {
            HttpStatus status = HttpStatus.valueOf(errorResponse.getStatusCode().value());
            ApiError body = new ApiError(status.value(), status.getReasonPhrase(), ex.getMessage());
            return ResponseEntity.status(status).body(body);
        }
        log.error("Unexpected error", ex);
        ApiError body = new ApiError(500, "Internal Server Error", "An unexpected error occurred");
        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Invalid value '" + ex.getValue() + "' for '" + ex.getName() + "'";
        if (ex.getRequiredType() == SubmissionStatus.class) {
            message = message + ". Allowed: " + Arrays.toString(SubmissionStatus.values());
        }
        ApiError body = new ApiError(400, "Bad Request", message);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + " " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ApiError body = new ApiError(400, "Bad Request", message);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleUnreadable(HttpMessageNotReadableException ex) {
        ApiError body = new ApiError(400, "Bad Request", "Request body is missing or not valid JSON");
        return ResponseEntity.badRequest().body(body);
    }
}

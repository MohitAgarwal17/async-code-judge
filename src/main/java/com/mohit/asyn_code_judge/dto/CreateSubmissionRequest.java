package com.mohit.asyn_code_judge.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateSubmissionRequest(
        @NotBlank String language
) {}
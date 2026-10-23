package com.mohit.asyn_code_judge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSubmissionRequest(
        @NotBlank String language,
        @NotNull Long userId,
        @NotNull Long problemId
) { }
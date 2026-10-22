package com.mohit.asyn_code_judge.dto;

import com.mohit.asyn_code_judge.entity.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateProblemRequest(

        @NotBlank @Size(max = 200)
        String title,

        @NotBlank @Size(max = 100)
        @Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$",
                message = "must be lowercase letters, digits and single hyphens (e.g. two-sum)")
        String slug,

        @NotBlank @Size(max = 10000)
        String description,

        @NotNull
        Difficulty difficulty,

        @NotNull @Positive
        Integer timeLimitMs,

        @NotNull @Positive
        Integer memoryLimitMb) {
}
package com.mohit.asyn_code_judge.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTestCaseRequest(

        @NotNull @Size(max = 100000)
        String input,

        @NotNull @Size(max = 100000)
        String expectedOutput,

        @NotNull
        Boolean sample) {
}
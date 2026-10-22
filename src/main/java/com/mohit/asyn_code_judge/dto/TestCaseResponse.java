package com.mohit.asyn_code_judge.dto;

import com.mohit.asyn_code_judge.entity.TestCase;

public record TestCaseResponse(Long id, String input, String expectedOutput, boolean sample) {

    public static TestCaseResponse from(TestCase t) {
        return new TestCaseResponse(t.getId(), t.getInput(), t.getExpectedOutput(), t.isSample());
    }
}
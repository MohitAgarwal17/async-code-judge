package com.mohit.asyn_code_judge.controller;

import com.mohit.asyn_code_judge.dto.CreateTestCaseRequest;
import com.mohit.asyn_code_judge.dto.TestCaseResponse;
import com.mohit.asyn_code_judge.entity.TestCase;
import com.mohit.asyn_code_judge.service.TestCaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/problems/{problemId}/test-cases")
public class TestCaseController {

    private final TestCaseService service;

    public TestCaseController(TestCaseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TestCaseResponse> create(@PathVariable Long problemId,
                                                   @Valid @RequestBody CreateTestCaseRequest req) {
        TestCase saved = service.create(problemId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(TestCaseResponse.from(saved));
    }

    @GetMapping
    public List<TestCaseResponse> listSamples(@PathVariable Long problemId) {
        return service.findSamples(problemId).stream()
                .map(TestCaseResponse::from)
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long problemId, @PathVariable Long id) {
        service.delete(problemId, id);
        return ResponseEntity.noContent().build();
    }
}
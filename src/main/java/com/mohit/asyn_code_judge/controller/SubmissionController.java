package com.mohit.asyn_code_judge.controller;

import com.mohit.asyn_code_judge.dto.CreateSubmissionRequest;
import com.mohit.asyn_code_judge.entity.Submission;
import com.mohit.asyn_code_judge.entity.SubmissionStatus;
import com.mohit.asyn_code_judge.service.SubmissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping
    public List<Submission> getAllSubmissions(@RequestParam(required = false)SubmissionStatus status) {
        return submissionService.getAllSubmissions(status);
    }

    @GetMapping("/{id}")
    public Submission getSubmission(@PathVariable Long id) {
        return submissionService.getSubmissionById(id);
    }

    @PostMapping
    public ResponseEntity<Submission> createSubmission(@Valid @RequestBody CreateSubmissionRequest request) {
        Submission saved=submissionService.createSubmission(request);
        URI location = URI.create("/api/submissions/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
    }
}
package com.mohit.asyn_code_judge.controller;

import com.mohit.asyn_code_judge.dto.CreateProblemRequest;
import com.mohit.asyn_code_judge.entity.Problem;
import com.mohit.asyn_code_judge.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemService service;

    public ProblemController(ProblemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Problem> create(@Valid @RequestBody CreateProblemRequest req) {
        Problem saved = service.create(req);
        URI location = URI.create("/api/problems/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping
    public List<Problem> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Problem get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Problem update(@PathVariable Long id, @Valid @RequestBody CreateProblemRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
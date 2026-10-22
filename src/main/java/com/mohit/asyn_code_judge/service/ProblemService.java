package com.mohit.asyn_code_judge.service;

import com.mohit.asyn_code_judge.dto.CreateProblemRequest;
import com.mohit.asyn_code_judge.entity.Problem;
import com.mohit.asyn_code_judge.exception.ProblemNotFoundException;
import com.mohit.asyn_code_judge.repository.ProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository repository;

    public ProblemService(ProblemRepository repository) {
        this.repository = repository;
    }

    public Problem create(CreateProblemRequest req) {
        Problem problem = new Problem(
                req.title(),
                req.slug(),
                req.description(),
                req.difficulty(),
                req.timeLimitMs(),
                req.memoryLimitMb());
        return repository.save(problem);
    }

    public List<Problem> findAll() {
        return repository.findAll();
    }

    public Problem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProblemNotFoundException(id));
    }

    @Transactional
    public Problem update(Long id, CreateProblemRequest req) {
        Problem problem = findById(id);
        problem.setTitle(req.title());
        problem.setDescription(req.description());
        problem.setDifficulty(req.difficulty());
        problem.setTimeLimitMs(req.timeLimitMs());
        problem.setMemoryLimitMb(req.memoryLimitMb());
        return problem;
    }

    public void delete(Long id) {
        Problem problem = findById(id);
        repository.delete(problem);
    }
}
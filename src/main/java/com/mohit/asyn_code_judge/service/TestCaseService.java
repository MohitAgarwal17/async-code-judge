package com.mohit.asyn_code_judge.service;

import com.mohit.asyn_code_judge.dto.CreateTestCaseRequest;
import com.mohit.asyn_code_judge.entity.Problem;
import com.mohit.asyn_code_judge.entity.TestCase;
import com.mohit.asyn_code_judge.exception.TestCaseNotFoundException;
import com.mohit.asyn_code_judge.repository.TestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {

    private final TestCaseRepository repository;
    private final ProblemService problemService;

    public TestCaseService(TestCaseRepository repository, ProblemService problemService) {
        this.repository = repository;
        this.problemService = problemService;
    }

    public TestCase create(Long problemId, CreateTestCaseRequest req) {
        Problem problem = problemService.findById(problemId);
        TestCase testCase = new TestCase(problem, req.input(), req.expectedOutput(), req.sample());
        return repository.save(testCase);
    }

    public List<TestCase> findSamples(Long problemId) {
        problemService.findById(problemId);
        return repository.findByProblemIdAndSampleTrue(problemId);
    }

    public void delete(Long problemId, Long id) {
        TestCase testCase = repository.findByIdAndProblemId(id, problemId)
                .orElseThrow(() -> new TestCaseNotFoundException(id));
        repository.delete(testCase);
    }
}
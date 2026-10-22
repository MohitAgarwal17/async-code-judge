package com.mohit.asyn_code_judge.repository;

import com.mohit.asyn_code_judge.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    List<TestCase> findByProblemIdAndSampleTrue(Long problemId);

    Optional<TestCase> findByIdAndProblemId(Long id, Long problemId);
}
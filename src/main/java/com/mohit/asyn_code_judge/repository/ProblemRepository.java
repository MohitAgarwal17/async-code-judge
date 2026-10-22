package com.mohit.asyn_code_judge.repository;

import com.mohit.asyn_code_judge.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
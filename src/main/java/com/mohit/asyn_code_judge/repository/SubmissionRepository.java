package com.mohit.asyn_code_judge.repository;

import com.mohit.asyn_code_judge.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository
        extends JpaRepository<Submission, Long> {
}
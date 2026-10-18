package com.mohit.asyn_code_judge.service;

import com.mohit.asyn_code_judge.dto.CreateSubmissionRequest;
import com.mohit.asyn_code_judge.entity.Submission;
import com.mohit.asyn_code_judge.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    public Submission createSubmission(CreateSubmissionRequest request) {
        Submission submission = new Submission();
        submission.setLanguage(request.language());
        submission.setStatus("QUEUED");
        return submissionRepository.save(submission);
    }
}
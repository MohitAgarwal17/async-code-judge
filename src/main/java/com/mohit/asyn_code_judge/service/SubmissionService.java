package com.mohit.asyn_code_judge.service;

import com.mohit.asyn_code_judge.dto.CreateSubmissionRequest;
import com.mohit.asyn_code_judge.entity.Submission;
import com.mohit.asyn_code_judge.entity.SubmissionStatus;
import com.mohit.asyn_code_judge.exception.SubmissionNotFoundException;
import com.mohit.asyn_code_judge.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public List<Submission> getAllSubmissions(SubmissionStatus status) {
        if(status == null) {
            return submissionRepository.findAll();
        }
        return submissionRepository.findByStatus(status);
    }

    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id).orElseThrow(() -> new SubmissionNotFoundException(id));
    }

    public Submission createSubmission(CreateSubmissionRequest request) {
        Submission submission = new Submission();
        submission.setLanguage(request.language());
        submission.setStatus(SubmissionStatus.QUEUED);
        return submissionRepository.save(submission);
    }

    public void deleteSubmission(Long id) {
        if(!submissionRepository.existsById(id)) {
            throw new SubmissionNotFoundException(id);
        }
        submissionRepository.deleteById(id);
    }
}
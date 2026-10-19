package com.mohit.asyn_code_judge.service;

import com.mohit.asyn_code_judge.dto.CreateSubmissionRequest;
import com.mohit.asyn_code_judge.entity.Submission;
import com.mohit.asyn_code_judge.entity.SubmissionStatus;
import com.mohit.asyn_code_judge.exception.SubmissionNotFoundException;
import com.mohit.asyn_code_judge.exception.UnsupportedLanguageException;
import com.mohit.asyn_code_judge.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    private final LanguagePolicy languagePolicy;

    public SubmissionService(SubmissionRepository submissionRepository, LanguagePolicy languagePolicy) {
        this.submissionRepository = submissionRepository;
        this.languagePolicy = languagePolicy;
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
        String language = request.language().toLowerCase(Locale.ROOT);
        Submission submission = new Submission();
        if(!languagePolicy.isSupported(language))
            throw new UnsupportedLanguageException(request.language());
        submission.setLanguage(language);
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
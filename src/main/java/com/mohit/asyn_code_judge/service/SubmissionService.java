package com.mohit.asyn_code_judge.service;

import com.mohit.asyn_code_judge.dto.CreateSubmissionRequest;
import com.mohit.asyn_code_judge.entity.Problem;
import com.mohit.asyn_code_judge.entity.Submission;
import com.mohit.asyn_code_judge.entity.SubmissionStatus;
import com.mohit.asyn_code_judge.entity.User;
import com.mohit.asyn_code_judge.exception.ProblemNotFoundException;
import com.mohit.asyn_code_judge.exception.SubmissionNotFoundException;
import com.mohit.asyn_code_judge.exception.UnsupportedLanguageException;
import com.mohit.asyn_code_judge.exception.UserNotFoundException;
import com.mohit.asyn_code_judge.repository.ProblemRepository;
import com.mohit.asyn_code_judge.repository.SubmissionRepository;
import com.mohit.asyn_code_judge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final LanguagePolicy languagePolicy;

    public SubmissionService(SubmissionRepository submissionRepository,
                             UserRepository userRepository,
                             ProblemRepository problemRepository,
                             LanguagePolicy languagePolicy) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.languagePolicy = languagePolicy;
    }

    public List<Submission> getAllSubmissions(SubmissionStatus status) {
        if (status == null) {
            return submissionRepository.findAll();
        }
        return submissionRepository.findByStatus(status);
    }

    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new SubmissionNotFoundException(id));
    }

    public Submission createSubmission(CreateSubmissionRequest request) {
        String language = request.language().toLowerCase(Locale.ROOT);
        if (!languagePolicy.isSupported(language)) {
            throw new UnsupportedLanguageException(request.language());
        }

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException(request.userId()));
        Problem problem = problemRepository.findById(request.problemId())
                .orElseThrow(() -> new ProblemNotFoundException(request.problemId()));

        Submission submission = new Submission();
        submission.setLanguage(language);
        submission.setStatus(SubmissionStatus.QUEUED);
        submission.setUser(user);
        submission.setProblem(problem);
        return submissionRepository.save(submission);
    }

    public void deleteSubmission(Long id) {
        if (!submissionRepository.existsById(id)) {
            throw new SubmissionNotFoundException(id);
        }
        submissionRepository.deleteById(id);
    }
}
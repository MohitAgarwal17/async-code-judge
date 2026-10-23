package com.mohit.asyn_code_judge.service;

import com.mohit.asyn_code_judge.dto.CreateUserRequest;
import com.mohit.asyn_code_judge.entity.User;
import com.mohit.asyn_code_judge.exception.UserNotFoundException;
import com.mohit.asyn_code_judge.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(CreateUserRequest req) {
        return userRepository.save(new User(req.username(), req.email()));
    }

    @Transactional(readOnly = true)
    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
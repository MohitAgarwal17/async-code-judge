package com.mohit.asyn_code_judge.repository;

import com.mohit.asyn_code_judge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
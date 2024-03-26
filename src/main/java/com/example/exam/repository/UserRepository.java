package com.example.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByEmail(String email);

}
package com.example.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.entity.ExamEntity;

public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

}

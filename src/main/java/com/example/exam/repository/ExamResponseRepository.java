package com.example.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.entity.ExamResponseEntity;

public interface ExamResponseRepository extends JpaRepository<ExamResponseEntity, Long> {
	
}

package com.example.exam.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	Set<QuestionEntity> findAllByExam_IdOrderByIdAsc(Long examId);
}

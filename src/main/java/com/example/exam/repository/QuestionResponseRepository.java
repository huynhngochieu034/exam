package com.example.exam.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.entity.QuestionResponseEntity;

public interface QuestionResponseRepository extends JpaRepository<QuestionResponseEntity, Long> {
	Set<QuestionResponseEntity> findAllByExamResponse_IdOrderByIdAsc(Long examResponseId);
}

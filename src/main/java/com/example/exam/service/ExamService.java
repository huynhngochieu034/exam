package com.example.exam.service;

import org.springframework.data.domain.Pageable;

import com.example.exam.dto.exam.CreateExamDTO;
import com.example.exam.dto.exam.ExamDTO;
import com.example.exam.utils.PagingResponse;

public interface ExamService {
	ExamDTO create(CreateExamDTO dto);
	PagingResponse<ExamDTO> findAll(Pageable pageable);
	ExamDTO findOneById(Long id);
	ExamDTO update(ExamDTO dto);
	String delete(Long id);
}

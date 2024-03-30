package com.example.exam.service;

import org.springframework.data.domain.Pageable;

import com.example.exam.dto.exam_response.CreateExamResponseDTO;
import com.example.exam.dto.exam_response.ExamResponseDTO;
import com.example.exam.utils.PagingResponse;

public interface ExamResponseService {
	Double submitExamResponse(CreateExamResponseDTO dto);
	PagingResponse<ExamResponseDTO> findAll(Pageable pageable);
	ExamResponseDTO findOneById(Long id);
}

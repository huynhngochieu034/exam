package com.example.exam.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exam.dto.exam_response.ExamResponseDTO;
import com.example.exam.entity.ExamResponseEntity;

@Component
public class ExamResponseConverter {

	@Autowired
	private ModelMapper modelMapper;

	public ExamResponseDTO convertToDto(ExamResponseEntity entity) {
		ExamResponseDTO result = modelMapper.map(entity, ExamResponseDTO.class);
		return result;
	}

	public ExamResponseEntity convertToEntity(ExamResponseDTO examDTO) {
		ExamResponseEntity result = modelMapper.map(examDTO, ExamResponseEntity.class);
		return result;
	}
	
	public void partialUpdate(ExamResponseDTO examDTO, ExamResponseEntity entity) {
		modelMapper.map(examDTO, entity);
	}
}
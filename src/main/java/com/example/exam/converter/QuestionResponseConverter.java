package com.example.exam.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exam.dto.question_response.QuestionResponseDTO;
import com.example.exam.entity.QuestionResponseEntity;

@Component
public class QuestionResponseConverter {

	@Autowired
	private ModelMapper modelMapper;

	public QuestionResponseDTO convertToDto(QuestionResponseEntity entity) {
		QuestionResponseDTO result = modelMapper.map(entity, QuestionResponseDTO.class);
		return result;
	}

	public QuestionResponseEntity convertToEntity(QuestionResponseDTO examDTO) {
		QuestionResponseEntity result = modelMapper.map(examDTO, QuestionResponseEntity.class);
		return result;
	}
	
	public void partialUpdate(QuestionResponseDTO examDTO, QuestionResponseEntity entity) {
		modelMapper.map(examDTO, entity);
	}
}
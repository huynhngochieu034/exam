package com.example.exam.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exam.dto.question.QuestionDTO;
import com.example.exam.entity.QuestionEntity;

@Component
public class QuestionConverter {

	@Autowired
	private ModelMapper modelMapper;

	public QuestionDTO convertToDto(QuestionEntity entity) {
		QuestionDTO result = modelMapper.map(entity, QuestionDTO.class);
		return result;
	}

	public QuestionEntity convertToEntity(QuestionDTO questionDTO) {
		QuestionEntity result = modelMapper.map(questionDTO, QuestionEntity.class);
		return result;
	}
	
	public void partialUpdate(QuestionDTO questionDTO, QuestionEntity entity) {
		modelMapper.map(questionDTO, entity);
	}
}
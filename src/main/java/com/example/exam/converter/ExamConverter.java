package com.example.exam.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exam.dto.exam.ExamDTO;
import com.example.exam.entity.ExamEntity;

@Component
public class ExamConverter {

	@Autowired
	private ModelMapper modelMapper;

	public ExamDTO convertToDto(ExamEntity entity) {
		ExamDTO result = modelMapper.map(entity, ExamDTO.class);
		return result;
	}

	public ExamEntity convertToEntity(ExamDTO examDTO) {
		ExamEntity result = modelMapper.map(examDTO, ExamEntity.class);
		return result;
	}
	
	public void partialUpdate(ExamDTO examDTO, ExamEntity entity) {
		modelMapper.map(examDTO, entity);
	}
}
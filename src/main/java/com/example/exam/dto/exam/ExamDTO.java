package com.example.exam.dto.exam;

import java.util.Set;

import com.example.exam.dto.AbstractDTO;
import com.example.exam.dto.question.QuestionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO extends AbstractDTO<ExamDTO> {

	private static final long serialVersionUID = 2135824540766903305L;
	
	private String name;
	private String description;
	private Set<QuestionDTO> questions;
}
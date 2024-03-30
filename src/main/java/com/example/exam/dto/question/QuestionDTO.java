package com.example.exam.dto.question;

import java.util.Set;

import com.example.exam.dto.AbstractDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO extends AbstractDTO<QuestionDTO> {

	private static final long serialVersionUID = 2135824540766903305L;
	
	private String content;
	private Set<String> options;
	private Set<String> correctAnswers;
}
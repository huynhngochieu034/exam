package com.example.exam.dto.question_response;

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
public class QuestionResponseDTO extends AbstractDTO<QuestionResponseDTO> {

	private static final long serialVersionUID = 3418049231157853335L;

	private QuestionDTO question;
	private Set<String> selectedOptions;
}
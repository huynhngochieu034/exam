package com.example.exam.dto.exam_response;

import java.util.Set;

import com.example.exam.dto.AbstractDTO;
import com.example.exam.dto.exam.ExamDTO;
import com.example.exam.dto.question_response.QuestionResponseDTO;
import com.example.exam.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponseDTO extends AbstractDTO<ExamResponseDTO> {

	private static final long serialVersionUID = 2135824540766903305L;

	private ExamDTO exam;
	private UserDTO user;
	private Set<QuestionResponseDTO> questionResponses;
	private Double grade;
}
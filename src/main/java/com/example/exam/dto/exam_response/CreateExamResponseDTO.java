package com.example.exam.dto.exam_response;



import java.util.Set;

import com.example.exam.dto.question_response.CreateQuestionResponseDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExamResponseDTO {

	@NotNull
	private Long examId;
	
	@NotNull
	@NotEmpty
	private Set<CreateQuestionResponseDTO> questionResponses;
}
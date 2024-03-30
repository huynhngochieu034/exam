package com.example.exam.dto.question_response;



import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionResponseDTO {
    private Long questionId;
	private Set<String> selectedOptions;
}
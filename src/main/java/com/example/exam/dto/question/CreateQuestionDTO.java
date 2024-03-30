package com.example.exam.dto.question;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionDTO {
	
	@NotNull
	private String content;
	
	@NotEmpty
	private Set<String> options;
	
	@NotEmpty
	private Set<String> correctAnswers;
}
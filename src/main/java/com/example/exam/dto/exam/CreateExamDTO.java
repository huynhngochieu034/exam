package com.example.exam.dto.exam;

import java.util.Set;

import com.example.exam.dto.question.CreateQuestionDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExamDTO {

	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	@NotEmpty
	private Set<CreateQuestionDTO> questions;
}
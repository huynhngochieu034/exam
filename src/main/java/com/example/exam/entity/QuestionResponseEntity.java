package com.example.exam.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "question_responses")
public class QuestionResponseEntity extends BaseEntity {

	private static final long serialVersionUID = 4018010730064052615L;

	@ManyToOne
	@JoinColumn(name = "exam_response_id")
	private ExamResponseEntity examResponse;
	
	private Set<String> selectedOptions;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private QuestionEntity question;

}

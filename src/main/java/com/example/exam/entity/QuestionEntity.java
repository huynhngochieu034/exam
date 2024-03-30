package com.example.exam.entity;

import java.util.Set;

import jakarta.persistence.Column;
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
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity {

	private static final long serialVersionUID = 4018010730064052615L;

	private String content;

	@Column(columnDefinition = "text[]")
	private Set<String> options;

	@Column(columnDefinition = "text[]")
	private Set<String> correctAnswers;

	@ManyToOne
	@JoinColumn(name = "exam_id")
	private ExamEntity exam;
}

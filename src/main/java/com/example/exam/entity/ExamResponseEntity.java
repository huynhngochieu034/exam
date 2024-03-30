package com.example.exam.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "exam_responses")
public class ExamResponseEntity extends BaseEntity {

	private static final long serialVersionUID = 8040913177459872161L;

	@ManyToOne
	@JoinColumn(name = "exam_id")
	private ExamEntity exam;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@OneToMany(mappedBy = "examResponse", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<QuestionResponseEntity> questionResponses;

}

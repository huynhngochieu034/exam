package com.example.exam.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exams")
public class ExamEntity extends BaseEntity {

	private static final long serialVersionUID = -4745972198889130160L;

	private String name;
	private String description;

	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<QuestionEntity> questions;

}

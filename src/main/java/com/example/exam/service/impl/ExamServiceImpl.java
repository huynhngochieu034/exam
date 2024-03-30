package com.example.exam.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exam.converter.ExamConverter;
import com.example.exam.converter.QuestionConverter;
import com.example.exam.dto.exam.CreateExamDTO;
import com.example.exam.dto.exam.ExamDTO;
import com.example.exam.dto.question.CreateQuestionDTO;
import com.example.exam.dto.question.QuestionDTO;
import com.example.exam.entity.ExamEntity;
import com.example.exam.entity.QuestionEntity;
import com.example.exam.entity.UserEntity;
import com.example.exam.enums.Role;
import com.example.exam.exception.BadRequestAlertException;
import com.example.exam.exception.NotFoundException;
import com.example.exam.repository.ExamRepository;
import com.example.exam.repository.QuestionRepository;
import com.example.exam.service.ExamService;
import com.example.exam.utils.PagingResponse;


@Service
public class ExamServiceImpl implements ExamService {

	private final Logger log = LoggerFactory.getLogger(ExamServiceImpl.class);

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ExamConverter examConverter;
	
	@Autowired
	private QuestionConverter questionConverter;

	@Override
	@Transactional
	public ExamDTO create(CreateExamDTO dto) {
		log.debug("Request to create exam : {}", dto);
		ExamEntity entity = new ExamEntity();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		ExamEntity examEntity = examRepository.save(entity);
		Set<QuestionEntity> questionEntitySet = new HashSet<>();
		for (CreateQuestionDTO question : dto.getQuestions()) {
			QuestionEntity questionEntity = QuestionEntity.builder()
					.content(question.getContent())
					.options(question.getOptions())
					.correctAnswers(question.getCorrectAnswers())
					.build();
			questionEntity.setExam(examEntity);
			questionEntitySet.add(questionEntity);
		}
		questionRepository.saveAll(questionEntitySet);
		return examConverter.convertToDto(examEntity);
	}

	@Override
	public PagingResponse<ExamDTO> findAll(Pageable paging) {
		Page<ExamEntity> page = examRepository.findAll(paging);
		List<ExamDTO> examDTOs = page.getContent().stream().map(item -> examConverter.convertToDto(item))
				.collect(Collectors.toList());
		return new PagingResponse<ExamDTO>(examDTOs, page.getNumber(), page.getTotalElements(), page.getTotalPages());
	}

	@Override
	@Transactional(readOnly = true)
	public ExamDTO findOneById(Long id) {
		log.debug("Request to find one exam : {}", id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserEntity userEntity = (UserEntity) authentication.getPrincipal();
		
		Optional<ExamEntity> examEntityOptional = examRepository.findById(id);		
		if (!examEntityOptional.isPresent()) {
			throw new NotFoundException("Exam not found!");
		}
		ExamEntity examEntity = examEntityOptional.get();
		Set<QuestionEntity> questions = questionRepository.findAllByExam_IdOrderByIdAsc(examEntity.getId());
		if (userEntity.getRole() == Role.TEACHER){
			examEntity.setQuestions(questions);
		} else {
			Set<QuestionEntity> questionEntities = questions.stream()
			        .peek(question -> question.setCorrectAnswers(null))
			        .collect(Collectors.toSet());
			examEntity.setQuestions(questionEntities);
		}
		
		return examConverter.convertToDto(examEntity);
	}

	@Override
	@Transactional
	public ExamDTO update(ExamDTO dto) {
		if (dto.getId() == null) {
			throw new BadRequestAlertException("Invalid id");
		}

		if (!examRepository.existsById(dto.getId())) {
			throw new NotFoundException("Exam not found!");
		}

		Optional<ExamEntity> examEntity = examRepository.findById(dto.getId()).map(existingExam -> {
			{
				Set<QuestionEntity> ex = existingExam.getQuestions();
				examConverter.partialUpdate(dto, existingExam);
				existingExam.setQuestions(ex);
				return existingExam;
			}
		}).map(examRepository::save);
		
		if (dto.getQuestions().size() > 0) {
	
			Set<QuestionEntity> questionEntitySet = new HashSet<>();
			Set<Long> updateIdsSet = new HashSet<>();
			for (QuestionDTO question : dto.getQuestions()) {
				QuestionEntity questionEntity = questionConverter.convertToEntity(question);
				questionEntity.setExam(examEntity.get());
				questionEntitySet.add(questionEntity);
				if (question.getId() != null) updateIdsSet.add(question.getId());
			}
		
			Set<QuestionEntity> questions = questionRepository.findAllByExam_IdOrderByIdAsc(examEntity.get().getId());
	        for (QuestionEntity existingQuestion : questions) {
	            if (!updateIdsSet.contains(existingQuestion.getId())) {
	                questionRepository.delete(existingQuestion);
	            }
	        }
	        
	        questionRepository.saveAll(questionEntitySet);
		}

		return examConverter.convertToDto(examEntity.get());
	}

	@Override
	@Transactional
	public String delete(Long id) {
		if (!examRepository.existsById(id)) {
			throw new NotFoundException("Exam not found!");
		}
		examRepository.deleteById(id);
		return "OK";
	}

}
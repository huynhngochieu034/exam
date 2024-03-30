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

import com.example.exam.converter.ExamResponseConverter;
import com.example.exam.converter.QuestionResponseConverter;
import com.example.exam.dto.exam_response.CreateExamResponseDTO;
import com.example.exam.dto.exam_response.ExamResponseDTO;
import com.example.exam.dto.question_response.CreateQuestionResponseDTO;
import com.example.exam.dto.question_response.QuestionResponseDTO;
import com.example.exam.entity.ExamEntity;
import com.example.exam.entity.ExamResponseEntity;
import com.example.exam.entity.QuestionEntity;
import com.example.exam.entity.QuestionResponseEntity;
import com.example.exam.entity.UserEntity;
import com.example.exam.exception.NotFoundException;
import com.example.exam.repository.ExamRepository;
import com.example.exam.repository.ExamResponseRepository;
import com.example.exam.repository.QuestionRepository;
import com.example.exam.repository.QuestionResponseRepository;
import com.example.exam.service.ExamResponseService;
import com.example.exam.utils.PagingResponse;


@Service
public class ExamResponseServiceImpl implements ExamResponseService {

	private final Logger log = LoggerFactory.getLogger(ExamResponseServiceImpl.class);

	@Autowired
	private ExamResponseRepository examResponseRepository;
	
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuestionResponseRepository questionResponseRepository;

	@Autowired
	private ExamResponseConverter examResponseConverter;
	
	@Autowired
	private QuestionResponseConverter questionResponseConverter;

	@Override
	@Transactional
	public Double submitExamResponse(CreateExamResponseDTO dto) {
		log.debug("Request to submit exam : {}", dto);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserEntity userEntity = (UserEntity) authentication.getPrincipal();
		
		Optional<ExamEntity> examEntity = examRepository.findById(dto.getExamId());
		if (!examEntity.isPresent()) {
			throw new NotFoundException("Incorrect exam id!");
		}
		
		ExamResponseEntity examResponseEntity = ExamResponseEntity.builder()
				.exam(examEntity.get())
				.user(userEntity)
				.build();
		examResponseEntity = examResponseRepository.save(examResponseEntity);
		
		Set<QuestionResponseEntity> questionResponseEntitySet = new HashSet<>();
		for (CreateQuestionResponseDTO questionResponse: dto.getQuestionResponses()) {
			Optional<QuestionEntity> questionEntity = questionRepository.findById(questionResponse.getQuestionId());
			if (!questionEntity.isPresent()) {
				throw new NotFoundException("Incorrect question id!");
			}
			
			QuestionResponseEntity questionResponseEntity = QuestionResponseEntity.builder()
					.examResponse(examResponseEntity)
					.question(questionEntity.get())
					.selectedOptions(questionResponse.getSelectedOptions())
					.build();
			questionResponseEntitySet.add(questionResponseEntity);
		}
		questionResponseRepository.saveAll(questionResponseEntitySet);
		return calculateGrade(examResponseEntity);
	}
	
	public Double calculateGrade(ExamResponseEntity examResponse) {
        Set<QuestionResponseEntity> questionResponses = questionResponseRepository.findAllByExamResponse_IdOrderByIdAsc(examResponse.getId());
        if (questionResponses == null || questionResponses.isEmpty()) {
            return 0.0;
        }

        int totalQuestions = questionResponses.size();
        int correctQuestions = 0;
        for (QuestionResponseEntity questionResponse : questionResponses) {
            Set<String> selectedOptions = questionResponse.getSelectedOptions();
            Set<String> correctAnswers = questionResponse.getQuestion().getCorrectAnswers();
            if (selectedOptions != null && correctAnswers != null && selectedOptions.containsAll(correctAnswers) && correctAnswers.containsAll(selectedOptions)) {
                correctQuestions++;
            }
        }

        return ((double) correctQuestions / totalQuestions) * 10.0;
    }

	@Override
	public PagingResponse<ExamResponseDTO> findAll(Pageable pageable) {
		Page<ExamResponseEntity> page = examResponseRepository.findAll(pageable);
		List<ExamResponseDTO> examDTOs = page.getContent().stream().map(item -> examResponseConverter.convertToDto(item))
				.collect(Collectors.toList());
		return new PagingResponse<ExamResponseDTO>(examDTOs, page.getNumber(), page.getTotalElements(), page.getTotalPages());
	}

	@Override
	public ExamResponseDTO findOneById(Long id) {
		log.debug("Request to find one exam response : {}", id);

		Optional<ExamResponseEntity> examEntityOptional = examResponseRepository.findById(id);		
		if (!examEntityOptional.isPresent()) {
			throw new NotFoundException("Exam response not found!");
		}
		ExamResponseEntity examEntity = examEntityOptional.get();
		Set<QuestionResponseEntity> questions = questionResponseRepository.findAllByExamResponse_IdOrderByIdAsc(examEntity.getId());
		Set<QuestionResponseDTO> qsSet = questions.stream()
		        .map(item -> questionResponseConverter.convertToDto(item))
		        .collect(Collectors.toSet());
		ExamResponseDTO responseDTO = examResponseConverter.convertToDto(examEntity);
		responseDTO.setQuestionResponses(qsSet);
		responseDTO.setGrade(calculateGrade(examEntity));
		return responseDTO;
	}
}
package com.example.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exam.dto.exam_response.CreateExamResponseDTO;
import com.example.exam.dto.exam_response.ExamResponseDTO;
import com.example.exam.service.ExamResponseService;
import com.example.exam.utils.CustomResponse;
import com.example.exam.utils.PagingResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/exam-responses")
public class ExamResponseController extends AbstractController {
	
	@Autowired
	private ExamResponseService examResponseService;
	
	@PostMapping
    public ResponseEntity<CustomResponse<Double>> submit(@RequestBody CreateExamResponseDTO dto) {
        return generateResponse(examResponseService.submitExamResponse(dto), HttpStatus.CREATED);
    }
	
	@GetMapping
    public ResponseEntity<CustomResponse<PagingResponse<ExamResponseDTO>>> findAll(Pageable pageable) {
		return generateResponse(examResponseService.findAll(pageable), HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ExamResponseDTO>> findById(@PathVariable Long id) {
        return generateResponse(examResponseService.findOneById(id), HttpStatus.OK);
    }

}

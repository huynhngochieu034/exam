package com.example.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exam.dto.exam.CreateExamDTO;
import com.example.exam.dto.exam.ExamDTO;
import com.example.exam.service.ExamService;
import com.example.exam.utils.CustomResponse;
import com.example.exam.utils.PagingResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/exams")
public class ExamController extends AbstractController {
	
	@Autowired
	private ExamService examService;
	
	@PostMapping
    public ResponseEntity<CustomResponse<ExamDTO>> create(@RequestBody CreateExamDTO dto) {
        return generateResponse(examService.create(dto), HttpStatus.CREATED);
    }
	
	@GetMapping
    public ResponseEntity<CustomResponse<PagingResponse<ExamDTO>>> findAll(Pageable pageable) {
		return generateResponse(examService.findAll(pageable), HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ExamDTO>> findById(@PathVariable Long id) {
        return generateResponse(examService.findOneById(id), HttpStatus.OK);
    }
	
	@PutMapping
    public ResponseEntity<CustomResponse<ExamDTO>> update(@RequestBody ExamDTO dto) {
        return generateResponse(examService.update(dto), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> delete(@PathVariable Long id) {
        return generateResponse(examService.delete(id), HttpStatus.NO_CONTENT);
    }

}

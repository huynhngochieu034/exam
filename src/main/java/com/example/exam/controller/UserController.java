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

import com.example.exam.dto.user.RegisterRequestDTO;
import com.example.exam.dto.user.UserDTO;
import com.example.exam.service.UserService;
import com.example.exam.utils.CustomResponse;
import com.example.exam.utils.PagingResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/users")
public class UserController extends AbstractController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
    public ResponseEntity<CustomResponse<UserDTO>> addUsers(@RequestBody RegisterRequestDTO dto) {
        return generateResponse(userService.register(dto), HttpStatus.CREATED);
    }
	
	@GetMapping
    public ResponseEntity<CustomResponse<PagingResponse<UserDTO>>> findAll(Pageable pageable) {
		return generateResponse(userService.findAll(pageable), HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<CustomResponse<UserDTO>> findById(@PathVariable Long id) {
        return generateResponse(userService.findOneById(id), HttpStatus.OK);
    }
	
	@PutMapping
    public ResponseEntity<CustomResponse<UserDTO>> update(@RequestBody UserDTO userDTO) {
        return generateResponse(userService.update(userDTO), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> delete(@PathVariable Long id) {
        return generateResponse(userService.delete(id), HttpStatus.NO_CONTENT);
    }

}

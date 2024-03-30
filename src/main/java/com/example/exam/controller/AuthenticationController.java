package com.example.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exam.dto.login.LoginRequestDTO;
import com.example.exam.dto.login.LoginResponseDTO;
import com.example.exam.service.impl.AuthenticationService;
import com.example.exam.utils.CustomResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController extends AbstractController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<CustomResponse<LoginResponseDTO>> authenticate(@RequestBody LoginRequestDTO request) {
		return generateResponse(authenticationService.authenticate(request), HttpStatus.OK);
	}
}
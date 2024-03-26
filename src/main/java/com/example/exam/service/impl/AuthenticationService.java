package com.example.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.exam.dto.LoginRequestDTO;
import com.example.exam.dto.RegisterRequestDTO;
import com.example.exam.dto.UserDTO;
import com.example.exam.entity.UserEntity;
import com.example.exam.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserDTO register(RegisterRequestDTO registerRequestDTO) {
		UserEntity user = UserEntity.builder()
	        .firstName(registerRequestDTO.getFirstName())
	        .lastName(registerRequestDTO.getLastName())
	        .email(registerRequestDTO.getEmail())
	        .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
	        .role(registerRequestDTO.getRole())
	        .build();
	    UserEntity userEntity = userRepository.save(user);
	    String jwtToken = jwtService.generateToken(user);
	    return UserDTO.builder().id(userEntity.getId()).email(userEntity.getEmail()).firstName(jwtToken)
				.lastName(jwtToken).role(userEntity.getRole()).accessToken(jwtToken).build();
	  }

	public UserDTO authenticate(LoginRequestDTO request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		UserEntity userEntity = userRepository.findByEmail(request.getEmail());
		if (userEntity == null) {
			throw new UsernameNotFoundException("email not found");
		}
		String jwtToken = jwtService.generateToken(userEntity);
		return UserDTO.builder().id(userEntity.getId()).email(userEntity.getEmail()).firstName(jwtToken)
				.lastName(jwtToken).role(userEntity.getRole()).accessToken(jwtToken).build();
	}
}
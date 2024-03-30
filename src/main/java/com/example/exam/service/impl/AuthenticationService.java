package com.example.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.exam.converter.UserConverter;
import com.example.exam.dto.login.LoginRequestDTO;
import com.example.exam.dto.login.LoginResponseDTO;
import com.example.exam.dto.user.RegisterRequestDTO;
import com.example.exam.dto.user.UserDTO;
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

	@Autowired
	private UserConverter userConverter;

	public UserDTO register(RegisterRequestDTO registerRequestDTO) {
		UserEntity user = new UserEntity();
		user.setFirstName(registerRequestDTO.getFirstName());
		user.setLastName(registerRequestDTO.getLastName());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRole(registerRequestDTO.getRole());

		UserEntity userEntity = userRepository.save(user);
		UserDTO userDTO = userConverter.convertToDto(userEntity);
		return userDTO;
	}

	public LoginResponseDTO authenticate(LoginRequestDTO request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		UserEntity userEntity = userRepository.findByEmail(request.getEmail());
		if (userEntity == null) {
			throw new UsernameNotFoundException("email not found");
		}
		String jwtToken = jwtService.generateToken(userEntity);
		UserDTO userDTO = userConverter.convertToDto(userEntity);
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setId(userDTO.getId());
		loginResponseDTO.setFirstName(userDTO.getFirstName());
		loginResponseDTO.setLastName(userDTO.getLastName());
		loginResponseDTO.setEmail(userDTO.getEmail());
		loginResponseDTO.setRole(userDTO.getRole());
		loginResponseDTO.setAccessToken(jwtToken);
		return loginResponseDTO;
	}
}
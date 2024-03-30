package com.example.exam.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exam.converter.UserConverter;
import com.example.exam.dto.user.RegisterRequestDTO;
import com.example.exam.dto.user.UserDTO;
import com.example.exam.entity.UserEntity;
import com.example.exam.enums.Role;
import com.example.exam.exception.BadRequestAlertException;
import com.example.exam.exception.EmailAlreadyUsedException;
import com.example.exam.exception.NotFoundException;
import com.example.exam.repository.UserRepository;
import com.example.exam.service.UserService;
import com.example.exam.utils.PagingResponse;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private AuthenticationService authenticationService;

	@Value("${init.user.email}")
	private String initUserEmail;

	@Value("${init.user.password}")
	private String initUserPassword;

	@Override
	public void init() {
		UserEntity userEntity = userRepository.findByEmail(initUserEmail);
		if (userEntity == null) {
			authenticationService.register(RegisterRequestDTO.builder().email(initUserEmail).password(initUserPassword)
					.firstName("test").lastName("test").role(Role.TEACHER).build());
		}
	}

	@Override
	public UserDTO register(RegisterRequestDTO dto) {
		log.debug("Request to register User : {}", dto);
		if (userRepository.findByEmail(dto.getEmail()) != null) {
			throw new EmailAlreadyUsedException();
		}
		UserDTO userDTO = authenticationService
				.register(RegisterRequestDTO.builder().email(dto.getEmail()).password(dto.getPassword())
						.firstName(dto.getFirstName()).lastName(dto.getLastName()).role(dto.getRole()).build());
		return userDTO;
	}

	@Override
	public PagingResponse<UserDTO> findAll(Pageable paging) {
		Page<UserEntity> page = userRepository.findAll(paging);
		List<UserDTO> userDTOs = page.getContent().stream().map(item -> userConverter.convertToDto(item))
				.collect(Collectors.toList());
		return new PagingResponse<UserDTO>(userDTOs, page.getNumber(), page.getTotalElements(), page.getTotalPages());
	}

	@Override
	public UserDTO findOneById(Long id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if (!userEntity.isPresent()) {
			throw new NotFoundException("User not found!");
		}
		return userConverter.convertToDto(userEntity.get());
	}

	@Override
	public UserDTO update(UserDTO dto) {
		if (dto.getId() == null) {
			throw new BadRequestAlertException("Invalid id");
		}

		if (!userRepository.existsById(dto.getId())) {
			throw new NotFoundException("User not found!");
		}

		Optional<UserEntity> userEntity = userRepository.findById(dto.getId()).map(existingUser -> {
			{
				userConverter.partialUpdate(dto, existingUser);
				return existingUser;
			}
		}).map(userRepository::save);

		return userConverter.convertToDto(userEntity.get());
	}

	@Override
	@Transactional
	public String delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new NotFoundException("User not found!");
		}
		userRepository.deleteById(id);
		return "OK";
	}
}
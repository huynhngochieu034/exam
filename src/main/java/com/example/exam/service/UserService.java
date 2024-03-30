package com.example.exam.service;

import org.springframework.data.domain.Pageable;

import com.example.exam.dto.user.RegisterRequestDTO;
import com.example.exam.dto.user.UserDTO;
import com.example.exam.utils.PagingResponse;

public interface UserService {
	void init();
	UserDTO register(RegisterRequestDTO dto);
	PagingResponse<UserDTO> findAll(Pageable pageable);
	UserDTO findOneById(Long id);
	UserDTO update(UserDTO dto);
	String delete(Long id);
	
}

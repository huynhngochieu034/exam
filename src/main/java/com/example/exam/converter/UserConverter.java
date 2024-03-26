package com.example.exam.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exam.dto.UserDTO;
import com.example.exam.entity.UserEntity;

@Component
public class UserConverter {

	@Autowired
	private ModelMapper modelMapper;

	public UserDTO convertToDto(UserEntity entity) {
		UserDTO result = modelMapper.map(entity, UserDTO.class);
		return result;
	}

	public UserEntity convertToEntity(UserDTO userDTO) {
		UserEntity result = modelMapper.map(userDTO, UserEntity.class);
		return result;
	}
}
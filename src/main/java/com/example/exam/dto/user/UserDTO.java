package com.example.exam.dto.user;

import com.example.exam.dto.AbstractDTO;
import com.example.exam.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends AbstractDTO<UserDTO> {

	private static final long serialVersionUID = 2135824540766903305L;
	
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
}
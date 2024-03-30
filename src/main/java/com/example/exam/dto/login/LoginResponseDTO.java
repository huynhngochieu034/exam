package com.example.exam.dto.login;

import com.example.exam.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	private String accessToken;
}
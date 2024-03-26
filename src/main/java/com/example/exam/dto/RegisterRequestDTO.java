package com.example.exam.dto;

import com.example.exam.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;
}
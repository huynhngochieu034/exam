package com.example.exam.dto;

import com.example.exam.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	
	@JsonProperty("access_token")
	private String accessToken;
}
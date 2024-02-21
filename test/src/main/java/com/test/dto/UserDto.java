package com.test.dto;

import com.test.annotation.UserAnnotation;

import lombok.Data;

@Data
public class UserDto {
	
	@UserAnnotation(country = "korean", gender = "male")
	private String name;
	
	private int age;
	
}

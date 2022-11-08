package com.validation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDto {
	@NotNull (message = "Enter valid Name")
	@Length(min = 3,message = "Enter valid Name")
	private String name;
	
	@Min(10)
	private Long mobile;
	
	@Email(message = "Enter valid Email Id")
	private String address;
}

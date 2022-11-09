package com.javajwt.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDto {
	
	@Length(min = 3,message = "Enter Valid Name")
	private String userName;
	@Length(min = 5, message = "Password should be atleast 5 chars long")
	private String passWord;
	@NotNull(message = "Enter Valid Address")
	private String address;
	@NotNull(message = "Enter Valid Name")
	private String name;
	@NotNull(message = "Enter Valid Mobile No")
	private Long mobile;
	@Min(18)
	@NotNull(message = "Enter Valid age")
	private int age;
}

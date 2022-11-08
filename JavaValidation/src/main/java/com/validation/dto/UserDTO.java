package com.validation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserDTO {
	@NotNull
	@Length(max = 10,min = 5,message = "Name length should be between 5 to 10 chars")
	private String name;
	
	@Max(60)
	@Min(30)
	private int age;
	
	@NotNull
	//@Pattern(regexp = "",message = "Enter valid Mobile No")
	private long mob;
	
	private String address;
	@Email(message = "Enter Valid Email ID")
	private String email;
}

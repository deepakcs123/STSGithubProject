package com.validation.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.validation.dto.UserDto;
import com.validation.entities.UserEntity;
import com.validation.exceptions.PowercutException;
import com.validation.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/user")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto userDto)
	{
		return new ResponseEntity<>(this.userService.saveData(userDto),HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/user/{id}")
	public ResponseEntity<UserEntity> findUser(@PathVariable("id") Long UserId)
	{
		if(UserId==1)
		{
			throw new PowercutException("6000","Power cut by Interns");
		}
		return new ResponseEntity<UserEntity>(this.userService.getUserbyId(UserId),HttpStatus.OK);
	}
	
	@GetMapping(path = "/users")
	public ResponseEntity<List<UserEntity>> getAllUsers()
	{
		return new ResponseEntity<List<UserEntity>>(this.userService.getAllUsers(), HttpStatus.OK);
	}
}

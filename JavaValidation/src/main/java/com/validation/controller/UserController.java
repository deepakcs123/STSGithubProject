package com.validation.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.validation.dto.UserDTO;
import com.validation.entities.User;
import com.validation.services.AppRuntimeException;
import com.validation.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path="/user")
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path="/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) throws AccountNotFoundException 
	{
		if(id==1)
		{
			throw new AccountNotFoundException(id+"");
		}
		if(id==2)
		{
			throw new AppRuntimeException("600","Hello this is custom exception");
		}
		return new ResponseEntity<User>(userService.getUserbyId(id),HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/user")
	public ResponseEntity<User> saveUser(@RequestBody @Valid UserDTO userdto) {
		return new ResponseEntity<>(userService.saveUser(userdto), HttpStatus.CREATED);
	}

}

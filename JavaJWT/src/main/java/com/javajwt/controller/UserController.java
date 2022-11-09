package com.javajwt.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.javajwt.dto.UserDto;
import com.javajwt.entities.User;
import com.javajwt.services.UserServices;

@RestController
public class UserController {

	@Autowired
	private UserServices userService;

	@PostMapping(path = "/user")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto user) {
		return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
	}

	@GetMapping(path = "/user/{id}")
	public ResponseEntity<User> findUser(@PathVariable("id") Long id) {
		return new ResponseEntity<User>(this.userService.getUser(id), HttpStatus.OK);
	}

	@GetMapping(path = "/users")
	public ResponseEntity<List<User>> findUsers() {
		return new ResponseEntity<List<User>>(this.userService.getUsers(), HttpStatus.OK);
	}

	@DeleteMapping(path = "/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long Id) {
		return new ResponseEntity<String>(this.userService.removeUser(Id), HttpStatus.OK);
	}

	@PutMapping(path = "/user/{id}")
	public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto user, @PathVariable("id") Long Id) {
		return new ResponseEntity<String>(this.userService.updateUser(user, Id), HttpStatus.OK);
	}
	
}

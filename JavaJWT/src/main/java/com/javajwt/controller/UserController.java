package com.javajwt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javajwt.dto.AuthRequest;
import com.javajwt.dto.AuthResponse;
import com.javajwt.dto.UserDto;
import com.javajwt.entities.User;
import com.javajwt.services.CustomUserdetailsService;
import com.javajwt.services.UserServices;
import com.javajwt.util.JwtUtil;

@RestController
public class UserController {

	@Autowired
	private UserServices userService;
	
	@Autowired
	private CustomUserdetailsService customUserDetails;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

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

	@PostMapping("/authToken")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		
		System.out.println(authRequest);
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassWord()));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Invalid username or password");
		}	
		 catch (BadCredentialsException e) {
				e.printStackTrace();
				throw new Exception("Invalid username or password");
			}	
		UserDetails UDetails = this.customUserDetails.loadUserByUsername(authRequest.getUserName());
		String token=this.jwtUtil.generateToken(UDetails);
		System.out.println("JWT Token: "+token);
		return ResponseEntity.ok(new AuthResponse(token));
	}

}

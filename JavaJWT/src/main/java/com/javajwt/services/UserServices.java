package com.javajwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javajwt.dto.UserDto;
import com.javajwt.entities.User;
import com.javajwt.repositories.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepo;

	// Save user
	public User saveUser(UserDto userDto) {
		User user = User.UserData(0l, userDto.getUserName(), userDto.getPassWord(), userDto.getAddress(),
				userDto.getName(), userDto.getMobile(), userDto.getAge());
		return this.userRepo.save(user);
	}

	// Get all user
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

	// Get single user by ID
	public User getUser(Long id) {
		return this.userRepo.findById(id).get();
	}

	// Remove single user by ID
	public String removeUser(Long Id) {
		this.userRepo.deleteById(Id);
		return ("User has been removed successfully");
	}

	// Update user
	public String updateUser(UserDto userDto, Long Id) {
		Optional<User> userchk = this.userRepo.findById(Id);
		if (!userchk.isEmpty()) {
			User user = User.UserData(0l, userDto.getUserName(), userDto.getPassWord(), userDto.getAddress(),
					userDto.getName(), userDto.getMobile(), userDto.getAge());
			this.userRepo.save(user);
		}
		return "User has been updated successfully";
	}

	// Validate user
	public boolean validateUser(String userId, String pass) {
		return this.userRepo.existsByuserName(userId);
	}
}

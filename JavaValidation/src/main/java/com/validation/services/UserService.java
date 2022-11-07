package com.validation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.validation.dto.UserDTO;
import com.validation.entities.User;
import com.validation.repository.UserRepository;
@Service
public class UserService {

	@Autowired
	private UserRepository userRep;

	public User saveUser(UserDTO userDto) {
		User user = User.builddata(0, userDto.getName(), userDto.getAge(), userDto.getMob(), userDto.getAddress());
		return userRep.save(user);
	}

	public List<User> getAllUsers() {
		return userRep.findAll();
	}

	public User getUserbyId(Long id) {
		return userRep.findByUserId(id);
	}
}

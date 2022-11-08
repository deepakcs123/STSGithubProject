package com.validation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.validation.dto.UserDto;
import com.validation.entities.UserEntity;
import com.validation.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	public UserEntity saveData(UserDto userdto)
	{
		
		UserEntity userEntity=UserEntity.EntityData(0l,userdto.getName(), userdto.getMobile(), userdto.getAddress());
		return this.userRepo.save(userEntity);
	}
	
	public UserEntity getUserbyId(Long id)
	{
		return this.userRepo.findById(id).get();
	}
	
	public List<UserEntity> getAllUsers()
	{
		return this.userRepo.findAll();
		
	}
	
}

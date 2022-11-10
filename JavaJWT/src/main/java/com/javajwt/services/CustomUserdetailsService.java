package com.javajwt.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javajwt.entities.User;
import com.javajwt.repositories.UserRepository;

@Service
public class CustomUserdetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserName(username);

		if (username.equals(user.getUserName()))
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(),
					new ArrayList<>());
		else
			throw new UsernameNotFoundException("User not found");

	}

}

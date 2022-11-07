package com.email.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.email.entities.EmailOtp;

@Repository
public interface EmailOtpRepository extends CrudRepository<EmailOtp, Integer> {

	
}

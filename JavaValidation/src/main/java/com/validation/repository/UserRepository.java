package com.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.validation.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(Long id);

}

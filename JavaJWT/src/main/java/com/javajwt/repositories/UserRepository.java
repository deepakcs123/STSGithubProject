package com.javajwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javajwt.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByuserName(String userId);

}

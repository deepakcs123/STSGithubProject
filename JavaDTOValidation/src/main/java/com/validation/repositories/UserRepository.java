package com.validation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.validation.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}

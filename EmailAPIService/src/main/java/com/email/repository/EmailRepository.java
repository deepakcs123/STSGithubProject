package com.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.email.entities.EmailEntity;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Integer> {

}

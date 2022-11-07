package com.sms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sms.entities.SMSEntity;

@Repository
public interface SMSRepo extends CrudRepository<SMSEntity, Integer> {

}

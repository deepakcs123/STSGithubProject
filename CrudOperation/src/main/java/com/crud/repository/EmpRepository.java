package com.crud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.entities.Employee;

@Repository
public interface EmpRepository extends CrudRepository<Employee, Integer> {

}

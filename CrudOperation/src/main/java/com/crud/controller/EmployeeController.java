package com.crud.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crud.entities.Employee;
import com.crud.repository.EmpRepository;
import com.github.javafaker.Faker;

@RestController
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	private EmpRepository emprep;

	@GetMapping("/employee/{id}")
	public ResponseEntity<Optional<Employee>> getEmpoyee(@PathVariable("id") int Id) {
		Optional<Employee> employee = emprep.findById(Id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employees = (List<Employee>) emprep.findAll();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(employees);
	}

	@PostMapping("/employee")
	public ResponseEntity<Employee> setEmployee(@RequestBody Employee emp) {
		Employee save = emprep.save(emp);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getEmpId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		emprep.deleteById(id);
		return "Data has been deleted successfully of Id " + id;

	}

	@DeleteMapping("/employee")
	public String deleteEmployees() {
		emprep.deleteAll();
		return "Data has been deleted successfully";
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp, @PathVariable("id") int empId) {

		Optional<Employee> employee = emprep.findById(empId);
		if (employee.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		emp.setEmpId(empId);
		emprep.save(emp);
		return ResponseEntity.noContent().build();

	}

	// Faker API
	@GetMapping("/SetData")
	public String setData() {
		Faker faker = new Faker();

		for (int i = 1; i <= 10; i++) {
			Employee emp = new Employee();
			emp.setEmpName(faker.name().fullName());
			emp.setEmpMobile(faker.phoneNumber().cellPhone());
			emp.setEmpDob(faker.date().birthday().toString());
			emp.setEmpUsername(faker.name().username());
			emp.setEmpPassword(faker.number().digit());
			emp.setEmpAddress(faker.address().fullAddress());
			emp.setEmpRoll("IT");
			emprep.save(emp);

		}
		return "Data Inserted";

	}
}

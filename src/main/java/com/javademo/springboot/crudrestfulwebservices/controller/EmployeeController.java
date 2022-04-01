package com.javademo.springboot.crudrestfulwebservices.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javademo.springboot.crudrestfulwebservices.exceptions.ResourceNotFoundException;
import com.javademo.springboot.crudrestfulwebservices.model.Employee;
import com.javademo.springboot.crudrestfulwebservices.repository.IEmployeeRepository;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private IEmployeeRepository employeeRepository;

	// GET api to get all employee details
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();

	}

	// POST api to create employee
	@PostMapping("/employees")
	public Employee createEmployee(@Validated @RequestBody Employee employee) {
		return employeeRepository.save(employee);

	}

	// GET api by id
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with given Id: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	// PUT api - update employee details
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id") long employeeId, @RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow();
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		employeeRepository.save(employee);
		return ResponseEntity.ok().body(employee);
	}

	// DELETE api - delete employee details by id
	@DeleteMapping("/employee/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}

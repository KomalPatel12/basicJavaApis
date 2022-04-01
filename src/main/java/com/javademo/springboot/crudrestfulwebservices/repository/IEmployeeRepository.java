package com.javademo.springboot.crudrestfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javademo.springboot.crudrestfulwebservices.model.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}

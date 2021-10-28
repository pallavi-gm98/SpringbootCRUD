package com.palla.springbootCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palla.springbootCRUD.model.Employee;
//jpa repository internally provide @Repository so no need to add @Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}

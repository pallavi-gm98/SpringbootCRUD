package com.palla.springbootCRUD.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palla.springbootCRUD.model.Employee;
import com.palla.springbootCRUD.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	private EmployeeService employeeservice;
	
	public EmployeeController(EmployeeService employeeservice) {
		super();
		this.employeeservice = employeeservice;
	}
	
	//build create employee REST API
	
	//RequestBody annotation allows us to retrive the request body and automatically convert it to java object
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeservice.saveEmployee(employee),HttpStatus.CREATED);		
	}
	
	//get all employees restAPI
	@GetMapping
	public List<Employee> getAllEmployee(){
		return employeeservice.getAllEmployee();	
	}
	
	//get employee by Id
	//localhost:8080/api/employee/1
	//we use ResponseEntity in return type bcoz we can add status,body,header
	//we used to preferred response of restAPI
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeId(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeservice.getEmployeeById(employeeId),HttpStatus.OK);		
	}
	
	//update RestAPI
	//localhost:8080/api/employee/1

	//we use ResponseEntity in return type bcoz we can add status,body,header
	//we used to preferred response of restAPI
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeservice.updateEmployee(employee, id),HttpStatus.OK);	
	}
	
	//delete RestAPI
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		//delete employee
		employeeservice.deleteEmployee(id);
		return new ResponseEntity<String>("employee deleted sucessfully",HttpStatus.OK);
		
	}

}

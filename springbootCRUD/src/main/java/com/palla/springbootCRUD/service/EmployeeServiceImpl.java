package com.palla.springbootCRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.palla.springbootCRUD.exception.ResourceNotFoundException;
import com.palla.springbootCRUD.model.Employee;
import com.palla.springbootCRUD.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	//don't want to add autowired annotation bcoz spring finds spring bean only 
	//one constructor and springboot it automatically autowired this dependency\
	private EmployeeRepository employeeRepo;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepo.save(employee);
	}


	@Override
	public List<Employee> getAllEmployee() {
		
		return employeeRepo.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepo.findById(id);
//		if(employee.isPresent()){
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "id", id);
//			
//		}
		//instead of doing above method we can also follow this
		return employeeRepo.findById(id).orElseThrow(() -> 
					new ResourceNotFoundException("Employee", "id", id));
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
	
		//need to check whether employee with given id is existing or not
		Employee existingEmployee = employeeRepo.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee", "id", id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		//save existing employee to database
		employeeRepo.save(existingEmployee);
		return existingEmployee;
	}


	@Override
	public void deleteEmployee(long id) {
		
		//check weather employee exist or not
		employeeRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee", "id", id));
		employeeRepo.deleteById(id);
		
	}

}

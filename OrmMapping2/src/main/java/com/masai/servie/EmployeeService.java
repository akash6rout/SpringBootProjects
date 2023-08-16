package com.masai.servie;

import java.util.List;

import com.masai.model.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee emp, Integer deptId);
	
	public Employee getEmpById(Integer id);
	
	public List<Employee> getAllEmps();
}

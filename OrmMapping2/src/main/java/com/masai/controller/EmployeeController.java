package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Employee;
import com.masai.servie.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empServ;
	
	@PostMapping("/employee/register/{deptId}")
	public ResponseEntity<Employee> addEmp(@RequestBody Employee emp, @PathVariable ("deptId") Integer deptId){
		return new ResponseEntity<Employee>(empServ.addEmployee(emp, deptId), HttpStatus.CREATED);
	}
	
	@GetMapping("/emp/{empId}")
	public ResponseEntity<Employee> getEmpById(@PathVariable ("empId") Integer empId){
		return new ResponseEntity<Employee>(empServ.getEmpById(empId), HttpStatus.OK);
	}
	
	@GetMapping("/emps")
	public ResponseEntity<List<Employee>> getAllEmp(){
		return new ResponseEntity<List<Employee>>(empServ.getAllEmps(), HttpStatus.OK);
	}
}

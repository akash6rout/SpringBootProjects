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
import com.masai.model.Department;
import com.masai.servie.DapartmentService;


@RestController
public class DepartmentController {

	@Autowired
	private DapartmentService deptServ;
	
	@PostMapping("/department/register")
	public ResponseEntity<Department> addDeprtmentHandler(@RequestBody Department dept){
		return new ResponseEntity<>(deptServ.addDepartment(dept),HttpStatus.CREATED);
	}
	
	@GetMapping("/dept/{deptId}")
	public ResponseEntity<Department> getDeptByIdHandler(@PathVariable ("deptId") Integer deptId){
		return new ResponseEntity<Department>(deptServ.getDepartmentById(deptId), HttpStatus.OK);
	}
	
	@GetMapping("/depts")
	public ResponseEntity<List<Department>> getAllDept(){
		return new ResponseEntity<List<Department>>(deptServ.getAllDept(), HttpStatus.OK);
	}
}

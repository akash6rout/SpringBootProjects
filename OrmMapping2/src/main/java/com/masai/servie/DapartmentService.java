package com.masai.servie;

import java.util.List;

import com.masai.model.Department;

public interface DapartmentService {

	public Department addDepartment(Department dept);
	
	public Department getDepartmentById(Integer id);
	
	public List<Department> getAllDept();
}

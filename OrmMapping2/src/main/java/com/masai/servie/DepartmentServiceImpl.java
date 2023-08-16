package com.masai.servie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Department;
import com.masai.repository.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements  DapartmentService{

	@Autowired
	private DepartmentRepo dRepo;
	
	@Override
	public Department addDepartment(Department dept) {
		return dRepo.save(dept);
	}

	@Override
	public Department getDepartmentById(Integer id) {
		return dRepo.findById(id).get();
	}

	@Override
	public List<Department> getAllDept() {
		return dRepo.findAll();
	}

}

package com.masai.servie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.NoEmployeesFound;
import com.masai.model.Department;
import com.masai.model.Employee;
import com.masai.repository.DepartmentRepo;
import com.masai.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo eRepo;
	
	@Autowired
	private DepartmentRepo dRepo;
	
	@Override
	public Employee addEmployee(Employee emp, Integer deptId) {
		
		Department dept = dRepo.findById(deptId).get();
		
		List<Employee> list = dept.getEmployees();
		
		list.add(emp);
		
		emp.setDept(dept);
		
		return eRepo.save(emp);
	}

	@Override
	public Employee getEmpById(Integer id) {
		
		//return eRepo.findById(id).get();
		Employee emp=eRepo.findById(id).get();
		if(emp!=null)
		{
			return emp;
		}
		else
		{
			throw new NoEmployeesFound("Employee Not Found in this ID");
		}
	}

	@Override
	public List<Employee> getAllEmps() {
		return eRepo.findAll();
	}

}

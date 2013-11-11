package com.systemsinmotion.orgchart.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemsinmotion.orgchart.dao.DepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentDao departmentDao;

	public Department findDepartmentById(Integer departmentId) {

		return this.departmentDao.findById(departmentId);
	}

	public void setDepartmentDao(DepartmentDao deparmentDao) {
		this.departmentDao = deparmentDao;
	}

	public List<Department> findAllDepartments() {
		return this.departmentDao.findAll();
	}

	public Integer storeDepartment(Department department) {
		
		//if parent department is not null and parent departmentID is null, then set the parent department to null 
		if(department.getParentDepartment() != null && department.getParentDepartment().getId() == null) {
			department.setParentDepartment(null);
		}

		return this.departmentDao.save(department);
	}

	public void removeDepartment(Department department) {
		this.departmentDao.delete(department);
	}
}

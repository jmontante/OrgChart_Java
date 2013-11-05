package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface IEmployeeDao {
		
	/**
	 * Deletes the <code>Employee</code> instance from the database.
	 * 
	 * @param employee
	 *            Employee instance to delete
	 */
	public abstract void delete(Employee employee);
		
	/**
	 * Returns all <code>Employee</code> instances as a <code>List</code>
	 * 
	 * @return List of Employees
	 */
	public abstract List<Employee> findAll();

	
	/**
	 * Returns a <code>Employee</code> instance having a given <code>id</code>
	 * value.
	 * 
	 * @param id
	 *            Identifier of <code>Employee</code> instance to find
	 * @return Single <code>Employee</code> instance. Null if not found.
	 */
	public abstract Employee findById(Integer id);

	/**
	 * Returns all <code>Employee</code> instances having a given <code>Department</code>
	 * object for department member.
	 * 
	 * @param department
	 *            Identifier of <code>Employee</code> instance to find
	 * @return Single <code>Employee</code> instance. Null if not found.
	 */
	public abstract List<Employee> findByDepartment(Department department);
	
	/**
	 * Returns a <code>Employee</code> instance having a given <code>email</code>
	 * value.
	 * 
	 * @param email
	 *            Identifier of <code>Employee</code> instance to find
	 * @return Single <code>Employee</code> instance. Null if not found.
	 */
	public abstract Employee findByEmail(String email);
	
	/**
	 * Returns all <code>Employee</code> instances having a given <code>Employee</code>
	 * object for manager member.
	 * 
	 * @param manager
	 *            Identifier of <code>Employee</code> instance to find
	 * @return Single <code>Employee</code> instance. Null if not found.
	 */
	public abstract List<Employee> findByManager(Employee manager);
	
	/**
	 * Saves a given <code>Employee</code instance and returns its generated
	 * id.
	 * 
	 * @param employee
	 *            The <code>Employee</code> instance to be saved
	 * @return The identifier (id) of the new <code>Employee</code> instance.
	 */
	public abstract Integer save(Employee employee);
	
	
	/**
	 * Updates an existing <code>Employee</code> instance with new values.
	 * 
	 * @param employee
	 *            The <code>Employee</code> instance to be updated
	 */
	public abstract void update(Employee employee);
	
}

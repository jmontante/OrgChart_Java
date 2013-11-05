package com.systemsinmotion.orgchart.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

/**
 * A data access object (DAO) providing persistence and search support for Employee entities. Transaction control of the
 * save(), update() and delete() operations must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.systemsinmotion.orgchart.entity.Employee
 */
@Repository("employeeDao")
public class EmployeeDao implements com.systemsinmotion.orgchart.dao.IEmployeeDao {

	/**
	 * @see org.slf4j.Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDao.class);

	/**
	 * @see org.springframework.orm.hibernate3.HibernateTemplate
	 */
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDAO#delete(com.systemsinmotion .orgchart.entity.Employee)
	 */
	@Override
	public void delete(Employee employee) {
		LOG.debug("deleting Employee instance with name: " + employee.getFirstName() + " " + employee.getLastName());
		this.hibernateTemplate.delete(employee);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDAO#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		LOG.debug("finding all Employee instances");
		return this.hibernateTemplate.find("from Employee order by lastName");
	}
 
	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDAO#findById(java.lang.Integer )
	 */
	@Override
	public Employee findById(Integer id) {
		LOG.debug("getting Employee instance with id: " + id);
		Employee emp = null;

		if (id != null) {
			emp = this.hibernateTemplate.get(Employee.class, id);
		}
		return emp;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department) {
		List<Employee> emps = Collections.EMPTY_LIST;
		
		if(department != null && department.getId() != null){
			DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
			criteria.add(Restrictions.eq("department.id",department.getId()));
			
			LOG.debug("Finding employee by department: " + department.getName());
			emps = this.hibernateTemplate.findByCriteria(criteria);
		}
		return emps;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDAO#findByEmail(java.lang.String )
	 */
	@Override
	public Employee findByEmail(String email) {
		LOG.debug("finding Employee instance by email: " + email);
		Employee emp = null;

		if (StringUtils.hasText(email)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<Employee> employees = this.hibernateTemplate.findByCriteria(criteria);

			if (null != employees && !employees.isEmpty()) {
				emp = employees.get(0);
			}
		}
		return emp;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDAO#findByManager(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByManager(Employee manager) {
		List<Employee> emps = Collections.EMPTY_LIST;
		
		if(manager != null && manager.getId() != null && manager.getIsManager() == true){
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("manager.id",manager.getId()));
			
			LOG.debug("Finding employee by manager: " + manager.getFirstName() + " " + manager.getLastName());
			emps = this.hibernateTemplate.findByCriteria(criteria);
		}
		return emps;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDAO#save(com.systemsinmotion .orgchart.entity.Employee)
	 */
	@Override
	public Integer save(Employee employee) {
		LOG.debug("saving Department instance with name: " + employee.getFirstName() + " " + employee.getLastName());
		return (Integer) this.hibernateTemplate.save(employee);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDAO#update(com.systemsinmotion .orgchart.entity.Employee)
	 */
	@Override
	public void update(Employee employee) {
		LOG.debug("updating Employee instance with ID: " + employee.getId().toString());
		this.hibernateTemplate.update(employee);
	}
}

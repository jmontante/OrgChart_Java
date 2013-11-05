package com.systemsinmotion.orgchart.dao;

//import java.util.Collections;
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

//import com.systemsinmotion.orgchart.entity.Department;
//import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

/**
 * A data access object (DAO) providing persistence and search support for JobTitle entities. Transaction control of the
 * save(), update() and delete() operations must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.systemsinmotion.orgchart.entity.Employee
 */
@Repository("jobTitleDao")
public class JobTitleDao implements IJobTitleDao{

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
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#delete(com.systemsinmotion .orgchart.entity.JobTitle)
	 */
	@Override
	public void delete(JobTitle jobTitle) {
		LOG.debug("deleting JobTitle instance with name: " + jobTitle.getName());
		this.hibernateTemplate.delete(jobTitle);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() {
		LOG.debug("finding all JobTitle instances");
		return this.hibernateTemplate.find("from JobTitle order by name");
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findById(java.lang.Integer )
	 */
	@Override
	public JobTitle findById(Integer id) {
		LOG.debug("getting Employee instance with id: " + id);
		JobTitle job = null;

		if (id != null) {
			job = this.hibernateTemplate.get(JobTitle.class, id);
		}
		return job;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findByName(java.lang.String )
	 */
	@Override
	public JobTitle findByName(String name) {
		LOG.debug("finding JobTitle instance by name: " + name);
		JobTitle job = null;

		if (StringUtils.hasText(name)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(JobTitle.class);
			criteria.add(Restrictions.eq("name", name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<JobTitle> jobTitles = this.hibernateTemplate.findByCriteria(criteria);

			if (null != jobTitles && !jobTitles.isEmpty()) {
				job = jobTitles.get(0);
			}
		}
		return job;
	}

	
//	@Override
//	public List<JobTitle> findByParentDepartment(JobTitle jobTitle) {
//		List<Department> depts = Collections.EMPTY_LIST;
//		Department department = jobTitle.getDepartment();
//		
//		if (department != null && department.getId() != null) {
//			DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
//			criteria.add(Restrictions.eq("parentDepartment.id", department.getId()));
//
//			LOG.debug("finding child Departments for Department: " + department.getName());
//			depts = this.hibernateTemplate.findByCriteria(criteria);
//		}
//		return depts;
//	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#save(com.systemsinmotion .orgchart.entity.JobTitle)
	 */
	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("saving JobTitle instance with name: " + jobTitle.getName());
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#update(com.systemsinmotion .orgchart.entity.JobTitle)
	 */
	@Override
	public void update(JobTitle jobTitle) {
		LOG.debug("updating Department instance with name: " + jobTitle.getName());
		this.hibernateTemplate.update(jobTitle);
	}

}

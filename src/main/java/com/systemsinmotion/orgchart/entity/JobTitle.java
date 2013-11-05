package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A data access object (DAO) providing persistence and search support for JobTitle entities. Transaction control of the
 * save(), update() and delete() operations must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.systemsinmotion.orgchart.entity.JobTitle
 */
@Entity
@Table(name = "JOB_TITLE")
public class JobTitle {
	
	private Integer id;
	//private Department department;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String name;
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false) 
	public Integer getId()
	{
		return this.id;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName()
	{
		return this.name;
	}

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
//	public Department getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(Department department) {
//		this.department = department;
//	}
//	
	
}

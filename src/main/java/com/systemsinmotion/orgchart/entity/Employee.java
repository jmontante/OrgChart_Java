package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	private Integer id;
	
	private Department department;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;
	
	private String email;
	
	private String skypeName;
	
	private boolean isManager;
	
	private Employee manager;
	
	private JobTitle jobTitle;
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public void setDepartment(Department department)
	{
		this.department = department;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setSkypeName(String skypeName)
	{
		this.skypeName = skypeName;
	}

	public void setIsManager(boolean isManager)
	{
		this.isManager = isManager;
	}	
	
	public void setManager(Employee manager)
	{
		this.manager = manager;
	}
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false) 
	public Integer getId()
	{
		return this.id;
	}
 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getDepartment()
	{
		return this.department;
	}
	
	@Column(name = "FIRST_NAME", nullable = false, length = 45)
	public String getFirstName()
	{
		return this.firstName;
	}
	
	@Column(name = "LAST_NAME", nullable = false, length = 45)
	public String getLastName()
	{
		return this.lastName;
	}
	
	@Column(name = "EMAIL", nullable = false, length = 45)
	public String getEmail()
	{
		return this.email;
	}
	
	@Column(name = "SKYPE_NAME", nullable = false, length = 45)
	public String getSkypeName()
	{
		return this.skypeName;
	}
	
	@Column(name = "IS_MANAGER", nullable = false, length = 45)
	public boolean getIsManager()
	{
		return this.isManager;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager()
	{
		return this.manager;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}	
}

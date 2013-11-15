package com.systemsinmotion.orgchart.web.controller;

//import java.util.ArrayList;
import java.util.List;

//import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.web.View;
import com.google.gson.Gson;

@Controller
public class DefaultController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

	/**wire the employeeService*/
	@Autowired
	EmployeeService employeeService;

	/**wire the departmentService*/
	@Autowired
	DepartmentService departmentService;

	/**wire the jobTitleService*/
	@Autowired
	JobTitleService jobTitleService;
	
	/**
	 * do get
	 * 
	 * @return sting
	 * 
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	/**
	 * do departments_get
	 * 
	 * @param model
	 * 			the department model
	 * @return sting 			
	 */
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {		
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	/**
	 * do departments_post
	 * @param department
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Model model){
		
		departmentService.storeDepartment(department);	
		
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);		
		
		List<JobTitle> jobtTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobtTitles);
		
		return "redirect:" + View.DEPARTMENTS;
	}
	
	/**
	 * do departments_delete
	 * @param id
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "depts", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(@RequestParam Integer id, Model model) {
		
		//remove the department
		this.departmentService.removeDepartment(this.departmentService.findDepartmentById(id));
		
		//redirect to departments page to reload appropriate form data
		return "redirect:" + View.DEPARTMENTS;
	}
	
	/**
	 * do departments_put
	 * @param department
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Department department, Model model)
	{
		//update the department
		this.departmentService.updatedepartment(department);
		
		//redirect to departments page to reload appropriate form data
		return "redirect:" + View.DEPARTMENTS;
	}
	
	/**
	 * do departments_prefillEditForm
	 * @param empId
	 * @return
	 */
	@RequestMapping(value = "depts/{deptId}", method = RequestMethod.GET)
	public @ResponseBody String doDepartmentss_preFillEditForm(@PathVariable Integer deptId) {
		Department dept = this.departmentService.findDepartmentById(deptId);
		Gson gson = new Gson();
		String json = gson.toJson(dept);
		return json;
	}
	
	/**
	 * sets the department service
	 * @param departmentService
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	/**
	 * do employees_get
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {	
		
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		
		List<JobTitle> jobtTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobtTitles);
		
		return View.EMPLOYEES;
	}

	/**
	 * do employees_post
	 * @param employee
	 * @param model
	 * @return string
	 */	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Model model){
		employeeService.storeEmployee(employee);	
		return "redirect:" + View.EMPLOYEES;
	}
	
	/**
	 * do employees_delete
	 * @param id
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "emps", method = RequestMethod.DELETE)
	public String doEmployees_DELETE(@RequestParam Integer id, Model model) {
		
		//remove the employee
		this.employeeService.removeEmployee(this.employeeService.findEmployeeById(id));
		
		//redirect to employee page to reload appropriate form data
		return "redirect:" + View.EMPLOYEES;
	}
	
	/**
	 * do employees_put.
	 * @param employee        
	 * @param model            
	 * @return string
	 */
	@RequestMapping(value = "emps", method = RequestMethod.PUT)
	public String doEmployees_PUT(Employee employee, Model model) {

		//update the employee
		this.employeeService.updateEmployee(employee);
		
		//redirect to employee page to reload approapriate form data
		return "redirect:" + View.EMPLOYEES;
	}
	
	/**
	 * do employees_prefillEditForm
	 * @param empId
	 * @return
	 */
	@RequestMapping(value = "emps/{empId}", method = RequestMethod.GET)
	public @ResponseBody String doEmployees_preFillEditForm(@PathVariable Integer empId) {
		Employee emp = this.employeeService.findEmployeeById(empId);
		Gson gson = new Gson();
		String json = gson.toJson(emp);
		return json;
	}
	
	/**
	 * sets the employee service
	 * @param employeeService
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}	
	
	/**
	 * do jobtitles_get
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobtitles);
		return View.JOB_TITLES;
	}
	
	/**
	 * do jobtitles_post
	 * @param jobTitle
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Model model){
		jobTitleService.storeJobTitle(jobTitle);		
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobtitles);		
		return "redirect:" + View.JOB_TITLES;
	}
	
	/**
	 * do jobtitles_delete
	 * @param id
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "jobs", method = RequestMethod.DELETE)
	public String doJobTitles_DELETE(@RequestParam Integer id, Model model) {
		
		//remove the jobtitle
		this.jobTitleService.removeJobTitle(this.jobTitleService.findJobTitleById(id));
		
		//redirect to jobtitles page to reload appropriate form data
		return "redirect:" + View.JOB_TITLES;
	}
	
	/**
	 * do jobtitles_put
	 * @param jobTitle
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitles_PUT(JobTitle jobTitle, Model model)
	{
		//update the job title
		this.jobTitleService.updateJobTitle(jobTitle);
				
		//redirect to jobtitles page to reload appropriate form data
		return "redirect:" + View.JOB_TITLES;
	}
	
	/**
	 * do jobtitles_prefillEditForm
	 * @param jobId
	 * @return
	 */
	@RequestMapping(value = "jobs/{jobId}", method = RequestMethod.GET)
	public @ResponseBody String doJobTitle_preFillEditForm(@PathVariable Integer jobId) {
		JobTitle job = this.jobTitleService.findJobTitleById(jobId);
		Gson gson = new Gson();
		String json = gson.toJson(job);
		return json;
	}
	
	/**
	 * sets the jobtitles service
	 * @param jobTitleService
	 */
	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}
}

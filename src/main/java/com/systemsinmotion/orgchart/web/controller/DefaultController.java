package com.systemsinmotion.orgchart.web.controller;

//import java.util.ArrayList;
import java.util.List;
//import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DefaultController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(DefaultController.class);

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	JobTitleService jobTitleService;
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {		
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Model model){
		
		departmentService.storeDepartment(department);	
		
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);		
		
		List<JobTitle> jobtTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobtTitles);
		
		return "redirect:" + View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(@RequestParam Integer id, Model model) {
		
		//remove the department
		this.departmentService.removeDepartment(this.departmentService.findDepartmentById(id));
		
		//redirect to departments page to reload appropriate form data
		return "redirect:" + View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

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

	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Model model){
		employeeService.storeEmployee(employee);	
		return "redirect:" + View.EMPLOYEES;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.DELETE)
	public String doEmployees_DELETE(@RequestParam Integer id, Model model) {
		
		//remove the employee
		this.employeeService.removeEmployee(this.employeeService.findEmployeeById(id));
		
		//redirect to employee page to reload appropriate form data
		return "redirect:" + View.EMPLOYEES;
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}	
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobtitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Model model){
		jobTitleService.storeJobTitle(jobTitle);		
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobtitles);		
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.DELETE)
	public String doJobTitles_DELETE(@RequestParam Integer id, Model model) {
		this.jobTitleService.removeJobTitle(this.jobTitleService.findJobTitleById(id));
		return View.JOB_TITLES;
	}
	
	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}
}

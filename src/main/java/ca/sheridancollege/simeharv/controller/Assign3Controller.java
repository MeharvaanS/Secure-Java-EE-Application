package ca.sheridancollege.simeharv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.simeharv.dao.EmployeeDatabaseAccess;
import ca.sheridancollege.simeharv.model.Employee;

@Controller
public class Assign3Controller {
	@Autowired
	private EmployeeDatabaseAccess cda;
	
	@PostMapping("/addConfirmation")
	public String confirmation(Model model, @ModelAttribute Employee employee) {
		String message = null;
		String objective = "add";
		long numberOfRows = 0;
		List<Employee> employees = cda.selectEmployees();
		boolean exists = true;
		
		for(int i=0;i<employees.size();i++) {
			if(employees.get(i).getEmployeeName().equals(employee.getEmployeeName())
			   && employees.get(i).getEmployeeEmail().equals(employee.getEmployeeEmail())
			   && employees.get(i).getEmployeePhone() == employee.getEmployeePhone()){
				exists=true;
				break;
			}else {
				exists=false;
			}
		}
		
		if(exists==false) {
			numberOfRows = cda.addEmployee(employee);
			if(numberOfRows>0) {
				message = "The following employee data was successfully added to the database.";
			}else {
				message = "The new employee data was not added to the database.";
			}
		}else {
			message="Sorry! This employee's data already exists.";
			objective="add";
		}	
		model.addAttribute("message", message);
		model.addAttribute("objective", objective);
		model.addAttribute("employee", employee);
		model.addAttribute("rows", numberOfRows);
		return "/admin/confirmation";	
	}
	
	@PostMapping("/editConfirmation")
	public String editConfirmation(Model model, @ModelAttribute Employee employee, @RequestParam int employeeNumber ) {
		String message = null;
		String objective = "edit";
		employee.setEmployeeNumber(employeeNumber);
		long numberOfRows = cda.updateEmployeeData(employee);
			if(numberOfRows>0) {
				message = "The following employee data was successfully edited in the database.";
			}else {
				message = "The employee data was not edited in the database.";
			}
			model.addAttribute("message", message);
			model.addAttribute("objective", objective);
			model.addAttribute("employee", employee);
			model.addAttribute("rows", numberOfRows);
			return "/admin/confirmation";	
		}
	
	@GetMapping("/deleteConfirmation/{employeeNumber}")
	public String deleteConfirmation(Model model, @PathVariable int employeeNumber ) {
		String message = null;
		String objective = "delete";
		Employee employee = cda.selectEmployeeByEmployeeNumber(employeeNumber);

		long numberOfRows = cda.deleteEmployeeData(employeeNumber);
			if(numberOfRows>0) {
				message = "The following employee data was successfully deleted in the database.";
			}else {
				message = "The employee data was not edited in the database.";
			}
			model.addAttribute("message", message);
			model.addAttribute("objective", objective);
			model.addAttribute("employee", employee);
			model.addAttribute("rows", numberOfRows);
			return "/admin/confirmation";	
		}	
	
	@GetMapping("/index")
	public String index() {
		return "/index";
	}
	
	@GetMapping("/admin/employeeDataInput")
	public String employeeDataInput(Model model) {
		model.addAttribute("employee", new Employee());
		return "/admin/employeeDataInput";
	}
	
	
	@GetMapping ("/admin/listOfEditableEmployees")
	public String editableListOfEmployees(Model model) {
		List<Employee> employees = cda.selectEmployees();
		model.addAttribute("employees", employees);
		
		return "/admin/listOfEditableEmployees";
	}
	
	@GetMapping ("/employee/listOfEmployees")
	public String listOfEmployees(Model model) {
		List<Employee> employees = cda.selectEmployees();
		model.addAttribute("employees", employees);
		
		return "/employee/listOfEmployees";
	}
	
	@GetMapping("/admin/employeeDataEdit/{employeeNumber}")
	public String employeeDataEdit(Model model, @PathVariable("employeeNumber") int employeeNumber) {
		Employee employee = cda.selectEmployeeByEmployeeNumber(employeeNumber);
		model.addAttribute("employee", employee);
		return "/admin/employeeDataEdit"; 
	}
	
	@GetMapping ("/employee/employeeDataSearch")
	public String employeeDataSearch(Model model, @RequestParam String search ){
		model.addAttribute("search",search);
		return "/employee/employeeDataSearch";
	}
	
	@PostMapping ("/employeeSearchResult")
	public String employeeSearchResult(Model model, @RequestParam String searchCriteria, @RequestParam String search ){
		List<Employee> employees;
		
		if(search.equals("email")) {
			employees = cda.selectEmployeeByEmployeeEmail(searchCriteria);
		}
		else if(search.equals("name")) {
			employees = cda.selectEmployeeByEmployeeName(searchCriteria);
		}else {
			long searchCriteriaPhone = Long.parseLong(searchCriteria);
			employees = cda.selectEmployeeByEmployeePhone(searchCriteriaPhone);
		}
		model.addAttribute("employees", employees);
		return "/employee/listOfEmployees";
	}
	
	@GetMapping("/employee/employeeAccess")
	public String employeeAccess() {
	return "/employee/employeeAccess";
	}
	
	@GetMapping("/admin/adminAccess")
	public String adminAccess() {
	return "/admin/adminAccess";
	}
	
	@GetMapping("/login")
	public String securityLogin() {
	return "login";
	}
	
}
	
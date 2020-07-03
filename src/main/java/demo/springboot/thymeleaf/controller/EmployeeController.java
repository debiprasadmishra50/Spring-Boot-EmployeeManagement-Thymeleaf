package demo.springboot.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.springboot.thymeleaf.entity.Employee;
import demo.springboot.thymeleaf.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model model) {
		
		List<Employee> employees = employeeService.findAll();
		
		model.addAttribute("employees", employees);
		
		return "list-employees"; 
	}
	
	// add mapping for /showFormForAdd
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		// create model attribute to bind the data
		Employee employee = new Employee();
		
		model.addAttribute("employee", employee);
		
		return "employee-form";
	}
	
	// add mapping for saving the employee : "/save"
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) { // for employee : refer th:object in employee-form.html
		
		// save the employee 
		employeeService.save(employee);
		
		// use redirect to prevent duplicate submission
		return "redirect:/employees/list";
	}
	
	// add mapping for "/showFormForUpdate"
	@GetMapping("/showFormForUpdate")
	public String showFormforUpdate(@RequestParam("employeeId") int id, Model model) {
		
		// get the employee from EmployeeService
		Employee employee = employeeService.findById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		
		// send over to our forms
		return "employee-form";
	}
	
	// add mapping for "/deleteEmployee"
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int id) {
		
		// delete the employee
		employeeService.deleteById(id);
		
		// redirect to /employees/list
		return "redirect:/employees/list";
	}
}










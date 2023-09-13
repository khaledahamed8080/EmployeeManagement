package com.example.springboot.thymeleafdemo.controller;

import com.example.springboot.thymeleafdemo.entity.Employee;
import com.example.springboot.thymeleafdemo.service.EmployeeService;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data
     private EmployeeService employeeService;

	 public EmployeeController(EmployeeService theEmployeeService){
      employeeService=theEmployeeService;
	 }
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
      List<Employee> theEmployees=employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
@GetMapping("/showformforAdd")
	public String showform(Model theModel){
		Employee emp=new Employee();
		theModel.addAttribute("employee",emp);
		return"employees/employees-form";
	}
   

    @PostMapping("/save")
	public String saveemployee(@ModelAttribute("employee") Employee theEmployee){
     employeeService.save(theEmployee);
	 return"redirect:/employees/list";
	}


    @GetMapping("/showformforUpdate")
	public String update(@RequestParam("employeeId") int theid,Model theModel){

     Employee theEmployee=employeeService.findById(theid);

	theModel.addAttribute("employee",theEmployee);

    return"employees/employees-form";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId")int  theid){
	    employeeService.deleteById(theid);
		return"redirect:/employees/list";
	}
}










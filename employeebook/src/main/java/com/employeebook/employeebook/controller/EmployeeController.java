package com.employeebook.employeebook.controller;

import com.employeebook.employeebook.Employee;
import com.employeebook.employeebook.exception.EmployeeAlreadyAddedException;
import com.employeebook.employeebook.exception.EmployeeNotFoundException;
import com.employeebook.employeebook.exception.EmployeeStorageIsFullException;
import com.employeebook.employeebook.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String fistName, @RequestParam("lastName") String lastName
            , @RequestParam("departmetn") int department, @RequestParam("salary") int salary) {
            return employeeService.addEmployee(fistName, lastName,department,salary);
    }
@GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String fistName, @RequestParam("lastName") String lastName) {
            return employeeService.removeEmployee(fistName, lastName);
    }
@GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
            return employeeService.findEmployee(firstName, lastName);
    }
@GetMapping("/print")
    public Employee print() {
        return employeeService.print();
    }
}

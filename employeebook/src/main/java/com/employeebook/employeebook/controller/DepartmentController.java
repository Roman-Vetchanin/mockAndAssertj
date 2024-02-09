package com.employeebook.employeebook.controller;

import com.employeebook.employeebook.Employee;
import com.employeebook.employeebook.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/{id}/salary/max")
    public Integer getEmployeeWithMaxSalary(@PathVariable("id") int department) {
        return departmentService.getMaxSalary(department);
    }
    @GetMapping("/{id}/salary/sum")
    public int getSumOfSalary(@RequestParam("departmentId") int department) {
        return departmentService.getSumOfSalary(department);
    }
    @GetMapping("/{id}/salary/min")
    public Integer getEmployeeWithMinSalary(@PathVariable("departmentId") int department) {
        return departmentService.getMinSalary(department);
    }
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesFromDepartment(@PathVariable("id") int department) {
        return departmentService.getEmployeesFromDepartment(department);
    }
    @GetMapping("/printDepartmentAll")
    public Map<Integer, List<Employee>> getEmployeesFromDepartment() {
        return departmentService.getEmployeesGroupByDepartment();
    }
}

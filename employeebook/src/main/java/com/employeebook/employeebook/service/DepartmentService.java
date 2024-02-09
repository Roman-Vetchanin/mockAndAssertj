package com.employeebook.employeebook.service;

import com.employeebook.employeebook.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Integer getMaxSalary(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .max(Integer::compareTo)
                .orElse(null);
    }

    public Integer getMinSalary(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .min(Integer::compareTo)
                .orElse(null);
    }

    public List<Employee> getEmployeesFromDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public int getSumOfSalary(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Map<Integer, List<Employee>> getEmployeesGroupByDepartment() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}

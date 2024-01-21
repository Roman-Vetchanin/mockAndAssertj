package com.employeebook.employeebook.service;


import com.employeebook.employeebook.Employee;
import com.employeebook.employeebook.exception.EmployeeAlreadyAddedException;
import com.employeebook.employeebook.exception.EmployeeNotFoundException;
import com.employeebook.employeebook.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        String key = buildKey(firstName, lastName);
        final int LIMIT = 3;
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        } else if (employees.size() >= LIMIT) {
            throw new EmployeeStorageIsFullException("Штат укомплектован");
        } else {
            employees.put(key, new Employee(firstName, lastName, department, salary));
        }
        return employees.get(key);
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        Employee employee = employees.remove(key);
        if (employee==null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee print() {
        return (Employee) employees.values();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private String buildKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}



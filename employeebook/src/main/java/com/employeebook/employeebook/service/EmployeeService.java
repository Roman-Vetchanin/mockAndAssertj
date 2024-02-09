package com.employeebook.employeebook.service;


import com.employeebook.employeebook.Employee;

import java.util.Collection;

public interface EmployeeService {


    Employee addEmployee(String firstName, String lastName, int department, int salary);


    Employee removeEmployee(String fistName, String lastName);

    Employee findEmployee(String fistName, String lastName);

    Employee print();

    Collection<Employee> findAll();
}

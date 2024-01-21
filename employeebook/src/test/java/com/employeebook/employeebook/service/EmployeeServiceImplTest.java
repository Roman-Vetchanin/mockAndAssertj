package com.employeebook.employeebook.service;

import com.employeebook.employeebook.Employee;
import com.employeebook.employeebook.exception.EmployeeAlreadyAddedException;
import com.employeebook.employeebook.exception.EmployeeNotFoundException;
import com.employeebook.employeebook.exception.EmployeeStorageIsFullException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.*;


class EmployeeServiceImplTest {

    private final EmployeeService employeeServiceIml = new EmployeeServiceImpl();

    @AfterEach
    public void afterEach() {
        Collection<Employee> employeesCopy = new ArrayList<>(employeeServiceIml.findAll());
        for (Employee employee : employeesCopy) {
            employeeServiceIml.removeEmployee(employee.getFirstName(), employee.getLastName());
        }
    }

    @BeforeEach
    public void beforeEach() {
        employeeServiceIml.addEmployee("Ivan", "Ivanov", 1, 30_000);
        employeeServiceIml.addEmployee("Sergey", "Sergeev", 2, 40_000);
    }

    @Test
    void addEmployeeTest() {
        Employee expected = new Employee("Roman", "Romanov", 3, 50_000);
        employeeServiceIml.addEmployee("Roman", "Romanov", 3, 50_000);
        assertThatNoException().isThrownBy(() -> employeeServiceIml.findEmployee("Roman", "Romanov"));
        assertThat(employeeServiceIml.findEmployee("Roman", "Romanov")).isEqualTo(expected);
        assertThat(employeeServiceIml.findAll()).contains(expected);
    }

    @Test
    void addEmployeeNegativeTest1() {
        employeeServiceIml.addEmployee("Roman", "Romanov", 3, 50_000);
        assertThat(employeeServiceIml.findAll()).hasSize(3);
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeServiceIml.addEmployee("Petr", "Petrov", 2, 40_000));
    }

    @Test
    void addEmployeeNegativeTest2() {
        Employee employee = new Employee("Roman", "Romanov", 3, 50_000);
        employeeServiceIml.addEmployee("Roman", "Romanov", 3, 50_000);
        assertThat(employeeServiceIml.findAll()).contains(employee);
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeServiceIml.addEmployee("Roman", "Romanov", 3, 50_000));
    }

    @Test
    void removeEmployeePositiveTest() {
        Employee employee = new Employee("Roman", "Romanov", 3, 50_000);
        employeeServiceIml.addEmployee("Roman", "Romanov", 3, 50_000);
        assertThat(employeeServiceIml.findAll()).contains(employee);
        employeeServiceIml.removeEmployee("Roman", "Romanov");
        assertThat(employeeServiceIml.findAll()).doesNotContain(employee);
    }

    @Test
    void removeEmployeeNegativeTest() {
        Employee employee = new Employee("Aleksandr", "Aleksandrov", 1, 50_000);
        assertThat(employeeServiceIml.findAll()).doesNotContain(employee);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeServiceIml.removeEmployee("Aleksandr", "Aleksandrov"));
    }

    @Test
    void findEmployeePositiveTest() {
        Employee employee = new Employee("Roman", "Romanov", 3, 60_000);
        employeeServiceIml.addEmployee("Roman", "Romanov", 3, 60_000);
        assertThat(employeeServiceIml.findAll()).contains(employee);
        assertThat(employeeServiceIml.findEmployee("Roman", "Romanov")).isEqualTo(employee);
    }

    @Test
    void findEmployeeNegativeTest() {
        Employee employee = new Employee("Roman", "Romanov", 3, 60_000);
        assertThat(employeeServiceIml.findAll()).doesNotContain(employee);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeServiceIml.findEmployee("Roman", "Romanov"));
    }

    @Test
    void findAllPositiveTest() {
        assertThat(employeeServiceIml.findAll())
                .containsExactlyInAnyOrder(
                        new Employee("Ivan", "Ivanov", 1, 30_000),
                        new Employee("Sergey", "Sergeev", 2, 40_000));
    }

}
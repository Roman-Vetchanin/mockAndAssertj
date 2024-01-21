package com.employeebook.employeebook.service;

import com.employeebook.employeebook.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    private final Collection<Employee> employees = List.of(
            new Employee("Ivan", "Ivanov", 1, 30_000),
            new Employee("Sergey", "Sergeev", 2, 40_000),
            new Employee("Roman", "Romanov", 3, 50_000));
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        when(employeeService.findAll()).thenReturn(employees);
    }

    @Test
    void getMaxSalaryTest() {
        int expected = 50_000;
        assertThat(departmentService.getMaxSalary(3)).isEqualTo(expected);
    }
    @Test
    void getMaxSalaryNegativeTest() {
        int expected = 50_000;
        assertThat(departmentService.getMaxSalary(2)).isNotEqualTo(expected);
        assertThat(departmentService.getMaxSalary(4)).isNull();
    }

    @Test
    void getMinSalaryTest() {
        int expected = 30_000;
        assertThat(departmentService.getMinSalary(1)).isEqualTo(expected);
    }
    @Test
    void getMinSalaryNegativeTest() {
        int expected = 40_000;
        assertThat(departmentService.getMinSalary(1)).isNotEqualTo(expected);
        assertThat(departmentService.getMinSalary(4)).isNull();
    }

    @Test
    void getEmployeesFromDepartment() {
        assertThat(departmentService.getEmployeesFromDepartment(1))
                .containsExactlyInAnyOrder(
                        new Employee("Ivan", "Ivanov", 1, 30_000));
    }

    @Test
    void getSumOfSalaryTest() {
        int expected = 30_000;
        assertThat(departmentService.getSumOfSalary(1)).isEqualTo(expected);
    }
    @Test
    void getSumOfSalaryNegativeTest() {
        assertThat(departmentService.getSumOfSalary(4)).isEqualTo(0);
    }


    @Test
    void getEmployeesGroupByDepartment() {
        assertThat(departmentService.getEmployeesGroupByDepartment()).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                1, List.of( new Employee("Ivan", "Ivanov", 1, 30_000)),
                2, List.of( new Employee("Sergey", "Sergeev", 2, 40_000)),
                3, List.of( new Employee("Roman", "Romanov", 3, 50_000))
                )
        );
    }
}
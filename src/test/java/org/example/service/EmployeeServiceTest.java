package org.example.service;

import org.example.model.Employee;
import org.example.repository.FileEmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private static EmployeeService employeeService;

    @BeforeAll
    static void setUp() {
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository("src/test/resources/test_data.csv");
        employeeService = new EmployeeService(fileEmployeeRepository);
    }
    @Test
    void fetchAllEmployees() {
        Set<Employee> employees = employeeService.fetchAllEmployees();

        Assertions.assertEquals(2, employees.size());
    }
}
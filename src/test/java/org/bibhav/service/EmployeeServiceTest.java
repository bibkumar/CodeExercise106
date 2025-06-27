package org.bibhav.service;

import org.bibhav.model.Employee;
import org.bibhav.repository.FileEmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * Test class to test functions of employee service.
 *
 * @author BibhavKumar
 */
class EmployeeServiceTest {
    private static EmployeeService employeeService;

    @BeforeAll
    static void setUp() {
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository("src/test/resources/test_data.csv");
        employeeService = new EmployeeService(fileEmployeeRepository);
    }

    @Test
    void fetchAllEmployees() {
        Set<Employee> employees = employeeService.getEmployees();

        Assertions.assertEquals(2, employees.size());
    }
}
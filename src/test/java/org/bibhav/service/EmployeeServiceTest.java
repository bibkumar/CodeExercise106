package org.bibhav.service;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;
import org.bibhav.repository.FileEmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * Test class for EmployeeService.
 * <p>
 * This class contains unit tests for the EmployeeService, ensuring that it correctly fetches employee data
 * from the repository and handles various scenarios such as fetching all employees.
 * It uses JUnit 5 for testing and includes assertions to verify expected outcomes.
 * </p>
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
    void fetchAllEmployees() throws ApplicationException, BadRequestException {
        Set<Employee> employees = employeeService.getEmployees();

        Assertions.assertEquals(2, employees.size());
    }
}